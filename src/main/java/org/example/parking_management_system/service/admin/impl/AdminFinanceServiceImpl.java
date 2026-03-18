package org.example.parking_management_system.service.admin.impl;

import org.example.parking_management_system.dto.admin.response.DashboardDTO;
import org.example.parking_management_system.dto.admin.response.FinancialStatisticsDTO;
import org.example.parking_management_system.dto.admin.response.FinancialStatisticsItemDTO;
import org.example.parking_management_system.dto.admin.response.PaymentRecordDTO;
import org.example.parking_management_system.mapper.admin.AdminPaymentMapper;
import org.example.parking_management_system.service.admin.AdminFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 管理员财务服务实现类
 * 
 * 提供财务统计功能的具体实现，包括按日、周、月的统计数据查询。
 * 支持按停车场筛选，并提供完整的时间序列数据（包含0值补全）。
 * 
 * @author parking_management_system
 * @version 1.0
 */
@Service
public class AdminFinanceServiceImpl implements AdminFinanceService {
    @Autowired
    private AdminPaymentMapper adminPaymentMapper;

    /**
     * 查询财务统计数据
     * 
     * 根据停车场ID、时间范围和统计类型，返回财务统计信息。
     * 统计类型支持：day（按天）、week（按周）、month（按月）。
     * 返回数据包括总金额、总笔数、平均金额以及图表数据。
     * 图表数据会补全时间范围内的所有时间点，没有数据的时间点金额和笔数为0。
     * 
     * @param parkingLotId 停车场ID，为null时统计所有停车场
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param type 统计类型：day-按天，week-按周，month-按月，默认按天
     * @return 财务统计结果DTO
     */
    @Override
    public FinancialStatisticsItemDTO findFinancialAll(Long parkingLotId, LocalDate startDate, LocalDate endDate, String type) {
        // 将日期转换为时间范围
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        
        // 查询总金额和总笔数
        BigDecimal totalAmount = adminPaymentMapper.findTotalAmount(parkingLotId, startDateTime, endDateTime);
        Long totalCount = adminPaymentMapper.findTotalCount(parkingLotId, startDateTime, endDateTime);
        
        // 处理null值
        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }
        if (totalCount == null) {
            totalCount = 0L;
        }
        
        // 根据统计类型查询图表数据
        List<Map<String, Object>> statisticsData;
        if ("week".equalsIgnoreCase(type)) {
            statisticsData = adminPaymentMapper.findWeeklyStatistics(parkingLotId, startDateTime, endDateTime);
        } else if ("month".equalsIgnoreCase(type)) {
            statisticsData = adminPaymentMapper.findMonthlyStatistics(parkingLotId, startDateTime, endDateTime);
        } else {
            // 默认按天统计
            statisticsData = adminPaymentMapper.findDailyStatistics(parkingLotId, startDateTime, endDateTime);
        }
        
        // 转换图表数据并补全缺失日期
        List<FinancialStatisticsDTO> chartData = buildChartData(statisticsData, startDate, endDate, type);
        
        // 构建返回结果
        FinancialStatisticsItemDTO result = new FinancialStatisticsItemDTO();
        result.setTotalAmount(totalAmount);
        result.setTotalCount(totalCount);
        result.setChartData(chartData);
        
        // 计算平均金额，避免除零错误
        if (totalCount > 0) {
            BigDecimal averageAmount = totalAmount.divide(
                BigDecimal.valueOf(totalCount), 
                2, 
                RoundingMode.HALF_UP
            );
            result.setAverageAmount(averageAmount);
        } else {
            result.setAverageAmount(BigDecimal.ZERO);
        }
        
        return result;
    }

    /**
     * 构建图表数据，补全时间范围内的所有时间点
     * 
     * @param statisticsData 数据库查询的统计数据
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param type 统计类型
     * @return 完整的图表数据列表
     */
    private List<FinancialStatisticsDTO> buildChartData(List<Map<String, Object>> statisticsData, 
                                                         LocalDate startDate, 
                                                         LocalDate endDate, 
                                                         String type) {
        // 将数据库结果转换为Map，key为日期字符串
        Map<String, Map<String, Object>> dataMap = statisticsData.stream()
            .collect(Collectors.toMap(
                map -> map.get("date").toString(),
                map -> map
            ));
        
        List<FinancialStatisticsDTO> chartData = new ArrayList<>();
        
        if ("week".equalsIgnoreCase(type)) {
            // 按周补全数据
            LocalDate current = startDate.with(DayOfWeek.MONDAY);
            while (!current.isAfter(endDate)) {
                chartData.add(createChartItem(current, dataMap));
                current = current.plusWeeks(1);
            }
        } else if ("month".equalsIgnoreCase(type)) {
            // 按月补全数据
            LocalDate current = startDate.withDayOfMonth(1);
            while (!current.isAfter(endDate)) {
                chartData.add(createChartItem(current, dataMap));
                current = current.plusMonths(1);
            }
        } else {
            // 按天补全数据
            LocalDate current = startDate;
            while (!current.isAfter(endDate)) {
                chartData.add(createChartItem(current, dataMap));
                current = current.plusDays(1);
            }
        }
        
        return chartData;
    }

    /**
     * 创建单个图表数据项
     * 
     * @param date 日期
     * @param dataMap 数据映射表
     * @return 图表数据项
     */
    private FinancialStatisticsDTO createChartItem(LocalDate date, Map<String, Map<String, Object>> dataMap) {
        FinancialStatisticsDTO item = new FinancialStatisticsDTO();
        item.setDate(date);
        
        String dateKey = date.toString();
        if (dataMap.containsKey(dateKey)) {
            Map<String, Object> data = dataMap.get(dateKey);
            item.setAmount((BigDecimal) data.get("amount"));
            item.setCount(((Number) data.get("count")).longValue());
        } else {
            // 没有数据的日期补0
            item.setAmount(BigDecimal.ZERO);
            item.setCount(0L);
        }
        
        return item;
    }

    /**
     * 分页查询支付记录
     * 
     * @param parkingLotId 停车场ID
     * @param plateNumber 车牌号
     * @param paymentMethod 支付方式
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param page 页码
     * @param pageSize 每页数量
     * @return 支付记录列表和总数
     */
    @Override
    public Map<String, Object> findPaymentRecords(Long parkingLotId, String plateNumber, String paymentMethod,
                                                   LocalDate startDate, LocalDate endDate, 
                                                   Integer page, Integer pageSize) {
        // 处理默认值
        if (page == null || page < 1) {
            page = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        
        // 转换日期为时间范围
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;
        
        // 计算偏移量
        int offset = (page - 1) * pageSize;
        
        // 查询支付记录
        List<Map<String, Object>> records = adminPaymentMapper.findPaymentRecordsWithDetails(
            parkingLotId, plateNumber, paymentMethod, startDateTime, endDateTime, offset, pageSize
        );
        
        // 查询总数
        Long total = adminPaymentMapper.countPaymentRecords(
            parkingLotId, plateNumber, paymentMethod, startDateTime, endDateTime
        );
        
        // 转换为DTO列表
        List<PaymentRecordDTO> list = records.stream().map(this::convertToPaymentRecordDTO).collect(Collectors.toList());
        
        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("page", page);
        result.put("pageSize", pageSize);
        
        return result;
    }

    /**
     * 获取运营数据看板
     * 
     * @param parkingLotId 停车场ID
     * @param dateType 日期类型
     * @return 运营数据看板
     */
    @Override
    public DashboardDTO getDashboard(Long parkingLotId, String dateType) {
        // 根据日期类型计算时间范围
        LocalDateTime startDateTime;
        LocalDateTime endDateTime = LocalDateTime.now();
        
        if ("today".equalsIgnoreCase(dateType)) {
            startDateTime = LocalDate.now().atStartOfDay();
        } else if ("week".equalsIgnoreCase(dateType)) {
            startDateTime = LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay();
        } else if ("month".equalsIgnoreCase(dateType)) {
            startDateTime = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        } else {
            // 默认今天
            startDateTime = LocalDate.now().atStartOfDay();
        }
        
        // 查询总收入
        BigDecimal totalRevenue = adminPaymentMapper.findTotalAmount(parkingLotId, startDateTime, endDateTime);
        if (totalRevenue == null) {
            totalRevenue = BigDecimal.ZERO;
        }
        
        // 查询总订单数
        Long totalOrders = adminPaymentMapper.findTotalCount(parkingLotId, startDateTime, endDateTime);
        if (totalOrders == null) {
            totalOrders = 0L;
        }
        
        // 查询总车辆数
        Long totalVehicles = adminPaymentMapper.countDistinctVehicles(parkingLotId, startDateTime, endDateTime);
        if (totalVehicles == null) {
            totalVehicles = 0L;
        }
        
        // 查询高峰时段
        List<Map<String, Object>> peakHoursData = adminPaymentMapper.findPeakHours(parkingLotId, startDateTime, endDateTime);
        List<DashboardDTO.PeakHourDTO> peakHours = peakHoursData.stream()
            .map(this::convertToPeakHourDTO)
            .collect(Collectors.toList());
        
        // 查询支付方式分布
        List<Map<String, Object>> distributionData = adminPaymentMapper.findPaymentMethodDistribution(
            parkingLotId, startDateTime, endDateTime
        );
        List<DashboardDTO.PaymentMethodDistributionDTO> paymentMethodDistribution = 
            convertToPaymentMethodDistribution(distributionData, totalOrders);
        
        // TODO: 计算平均占用率（需要停车位数据，后续完善）
        BigDecimal averageOccupancyRate = BigDecimal.ZERO;
        
        // 构建返回结果
        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setTotalRevenue(totalRevenue);
        dashboard.setTotalOrders(totalOrders);
        dashboard.setTotalVehicles(totalVehicles);
        dashboard.setAverageOccupancyRate(averageOccupancyRate);
        dashboard.setPeakHours(peakHours);
        dashboard.setPaymentMethodDistribution(paymentMethodDistribution);
        
        return dashboard;
    }

    /**
     * 转换Map为PaymentRecordDTO
     * 
     * @param map 数据库查询结果
     * @return PaymentRecordDTO
     */
    private PaymentRecordDTO convertToPaymentRecordDTO(Map<String, Object> map) {
        PaymentRecordDTO dto = new PaymentRecordDTO();
        dto.setId(((Number) map.get("id")).longValue());
        dto.setParkingLotName((String) map.get("parkingLotName"));
        dto.setPlateNumber((String) map.get("plateNumber"));
        dto.setAmount((BigDecimal) map.get("amount"));
        dto.setPaymentMethod((String) map.get("paymentMethod"));
        dto.setPaymentMethodText(getPaymentMethodText((String) map.get("paymentMethod")));
        dto.setTransactionId((String) map.get("transactionId"));
        dto.setCreatedAt((LocalDateTime) map.get("createdAt"));
        return dto;
    }

    /**
     * 转换Map为PeakHourDTO
     * 
     * @param map 数据库查询结果
     * @return PeakHourDTO
     */
    private DashboardDTO.PeakHourDTO convertToPeakHourDTO(Map<String, Object> map) {
        DashboardDTO.PeakHourDTO dto = new DashboardDTO.PeakHourDTO();
        dto.setHour(map.get("hour").toString());
        dto.setCount(((Number) map.get("count")).longValue());
        return dto;
    }

    /**
     * 转换支付方式分布数据并计算百分比
     * 
     * @param distributionData 数据库查询结果
     * @param totalOrders 总订单数
     * @return PaymentMethodDistributionDTO列表
     */
    private List<DashboardDTO.PaymentMethodDistributionDTO> convertToPaymentMethodDistribution(
            List<Map<String, Object>> distributionData, Long totalOrders) {
        return distributionData.stream().map(map -> {
            DashboardDTO.PaymentMethodDistributionDTO dto = new DashboardDTO.PaymentMethodDistributionDTO();
            dto.setMethod((String) map.get("method"));
            Long count = ((Number) map.get("count")).longValue();
            dto.setCount(count);
            
            // 计算百分比
            if (totalOrders > 0) {
                BigDecimal percentage = BigDecimal.valueOf(count)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(totalOrders), 0, RoundingMode.HALF_UP);
                dto.setPercentage(percentage.intValue());
            } else {
                dto.setPercentage(0);
            }
            
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 获取支付方式文本
     * 
     * @param paymentMethod 支付方式代码
     * @return 支付方式文本
     */
    private String getPaymentMethodText(String paymentMethod) {
        if (paymentMethod == null) {
            return "未知";
        }
        switch (paymentMethod.toLowerCase()) {
            case "wechat":
                return "微信支付";
            case "alipay":
                return "支付宝";
            case "balance":
                return "余额支付";
            default:
                return paymentMethod;
        }
    }
}
