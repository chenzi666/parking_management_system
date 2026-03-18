package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.response.DashboardDTO;
import org.example.parking_management_system.dto.admin.response.FinancialStatisticsItemDTO;
import org.example.parking_management_system.dto.admin.response.PaymentRecordDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 * 管理员财务服务接口
 * 
 * 定义财务相关的业务逻辑接口，主要包括：
 * - 财务数据统计
 * - 收入分析
 * - 交易数据分析
 * - 运营数据看板
 * 
 * @author parking_management_system
 * @version 1.0
 */
public interface AdminFinanceService {
    /**
     * 查询财务统计数据
     * 
     * 根据指定的时间范围和筛选条件，统计财务数据，包括：
     * 1. 总体统计：
     *    - 总收入金额（totalAmount）
     *    - 总交易笔数（totalCount）
     *    - 平均交易金额（averageAmount）
     * 2. 按日期分组的详细数据（chartData）：
     *    - 每日收入金额
     *    - 每日交易笔数
     * 
     * @param parkingLotId 停车场ID，可为null表示统计所有停车场
     * @param startDate 统计开始日期（包含）
     * @param endDate 统计结束日期（包含）
     * @param type 统计类型：day-按天，week-按周，month-按月
     * @return FinancialStatisticsItemDTO 财务统计数据对象，包含总体统计和详细图表数据
     */
    FinancialStatisticsItemDTO findFinancialAll(Long parkingLotId, LocalDate startDate, LocalDate endDate, String type);

    /**
     * 分页查询支付记录
     * 
     * 根据筛选条件分页查询支付记录，支持按停车场、车牌号、支付方式、时间范围等条件过滤
     * 
     * @param parkingLotId 停车场ID，可为null表示所有停车场
     * @param plateNumber 车牌号，支持模糊查询
     * @param paymentMethod 支付方式
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param page 页码（从1开始）
     * @param pageSize 每页数量
     * @return 包含支付记录列表和总数的Map
     */
    Map<String, Object> findPaymentRecords(Long parkingLotId, String plateNumber, String paymentMethod,
                                           LocalDate startDate, LocalDate endDate, Integer page, Integer pageSize);

    /**
     * 获取运营数据看板
     * 
     * 提供运营数据的综合展示，包括：
     * - 总收入、总订单数、总车辆数
     * - 平均占用率
     * - 高峰时段分析
     * - 支付方式分布
     * 
     * @param parkingLotId 停车场ID，可为null表示所有停车场
     * @param dateType 日期类型：today-今天，week-本周，month-本月
     * @return 运营数据看板DTO
     */
    DashboardDTO getDashboard(Long parkingLotId, String dateType);
}
