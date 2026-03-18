package org.example.parking_management_system.controller.admin;

import org.example.parking_management_system.dto.admin.response.DashboardDTO;
import org.example.parking_management_system.dto.admin.response.FinancialStatisticsItemDTO;
import org.example.parking_management_system.service.admin.AdminFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员财务统计控制器
 * 
 * 提供财务数据统计相关的API接口，包括收入统计、交易统计、运营数据看板等功能。
 * 所有接口均需要管理员权限访问。
 * 
 * @author parking_management_system
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/admin/")
public class AdminFinanceController {
    /**
     * 财务服务接口
     * 处理所有与财务统计相关的业务逻辑
     */
    @Autowired
    private AdminFinanceService adminFinanceService;
    /**
     * 获取财务统计数据
     * 
     * 根据指定的时间范围和停车场ID，统计财务数据，包括：
     * - 总收入金额
     * - 总交易笔数
     * - 平均交易金额
     * - 按日期分组的详细统计数据（用于图表展示）
     * 
     * @param parkingLotId 停车场ID（可选），不传则统计所有停车场
     * @param startDate 开始日期，格式：yyyy-MM-dd
     * @param endDate 结束日期，格式：yyyy-MM-dd
     * @param type 统计类型：day-按天，week-按周，month-按月（默认按天）
     * @return 包含统计数据的响应对象
     *         - code: 响应状态码（200表示成功）
     *         - message: 响应消息
     *         - data: FinancialStatisticsItemDTO对象，包含详细统计信息
     */
    @GetMapping("/finance/statistics")
    public Map<String,Object> getFinancialStatistics(
            @RequestParam(value = "parkingLotId",required = false) Long parkingLotId,
            String startDate,
            String endDate,
            @RequestParam(value = "type",required = false, defaultValue = "day") String type)
    {
        // 创建响应结果容器
        Map<String,Object> result = new HashMap<>();
        
        // 调用服务层查询财务统计数据，将字符串日期转换为LocalDate对象
        FinancialStatisticsItemDTO financialStatisticsItemDTO =
                adminFinanceService.findFinancialAll(parkingLotId, LocalDate.parse(startDate),LocalDate.parse(endDate),type);
        
        // 封装响应结果
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data",financialStatisticsItemDTO);
        return result;
    }

    /**
     * 获取收费记录
     * 
     * 分页查询收费记录，支持多种筛选条件：
     * - 按停车场筛选
     * - 按车牌号模糊搜索
     * - 按支付方式筛选
     * - 按时间范围筛选
     * 
     * @param parkingLotId 停车场ID（可选）
     * @param plateNumber 车牌号（可选，支持模糊搜索）
     * @param paymentMethod 支付方式（可选）：wechat/alipay/balance
     * @param startDate 开始日期（可选），格式：yyyy-MM-dd
     * @param endDate 结束日期（可选），格式：yyyy-MM-dd
     * @param page 页码（默认1）
     * @param pageSize 每页数量（默认10）
     * @return 包含支付记录列表和分页信息的响应对象
     *         - code: 响应状态码
     *         - message: 响应消息
     *         - data: 包含list（记录列表）、total（总数）、page、pageSize
     */
    @GetMapping("/finance/payment-records")
    public Map<String, Object> getPaymentRecords(
            @RequestParam(value = "parkingLotId", required = false) Long parkingLotId,
            @RequestParam(value = "plateNumber", required = false) String plateNumber,
            @RequestParam(value = "paymentMethod", required = false) String paymentMethod,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        
        // 转换日期字符串为LocalDate
        LocalDate start = startDate != null && !startDate.isEmpty() ? LocalDate.parse(startDate) : null;
        LocalDate end = endDate != null && !endDate.isEmpty() ? LocalDate.parse(endDate) : null;
        
        // 调用服务层查询
        Map<String, Object> data = adminFinanceService.findPaymentRecords(
            parkingLotId, plateNumber, paymentMethod, start, end, page, pageSize
        );
        
        // 构建响应结果
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", data);
        
        return result;
    }

    /**
     * 导出收费记录
     * 
     * 根据筛选条件导出收费记录为Excel文件
     * 
     * @param parkingLotId 停车场ID（可选）
     * @param startDate 开始日期（可选），格式：yyyy-MM-dd
     * @param endDate 结束日期（可选），格式：yyyy-MM-dd
     * @return Excel文件下载
     * 
     * 注：此接口返回文件流，具体实现需要配置HttpServletResponse
     * TODO: 实现Excel导出功能
     */
    @GetMapping("/finance/export")
    public Map<String, Object> exportPaymentRecords(
            @RequestParam(value = "parkingLotId", required = false) Long parkingLotId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        
        // TODO: 实现Excel导出功能
        // 1. 查询数据
        // 2. 使用POI或EasyExcel生成Excel文件
        // 3. 设置响应头
        // 4. 返回文件流
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "导出功能开发中");
        result.put("data", null);
        
        return result;
    }

    /**
     * 获取运营数据看板
     * 
     * 提供运营数据的综合展示，包括：
     * - 总收入、总订单数、总车辆数
     * - 平均占用率
     * - 高峰时段分析（按小时统计订单数）
     * - 支付方式分布（各支付方式的订单数量和占比）
     * 
     * @param parkingLotId 停车场ID（可选），不传则统计所有停车场
     * @param dateType 日期类型：today-今天，week-本周，month-本月（默认today）
     * @return 包含运营数据的响应对象
     *         - code: 响应状态码
     *         - message: 响应消息
     *         - data: DashboardDTO对象，包含各项运营指标
     */
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard(
            @RequestParam(value = "parkingLotId", required = false) Long parkingLotId,
            @RequestParam(value = "dateType", required = false, defaultValue = "today") String dateType) {
        
        // 调用服务层获取看板数据
        DashboardDTO dashboard = adminFinanceService.getDashboard(parkingLotId, dateType);
        
        // 构建响应结果
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", dashboard);
        
        return result;
    }
}
