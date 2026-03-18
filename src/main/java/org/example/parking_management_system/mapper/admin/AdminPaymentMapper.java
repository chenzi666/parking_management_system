package org.example.parking_management_system.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.example.parking_management_system.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface AdminPaymentMapper {
    /**
     * 根据日期范围查询支付记录
     * @param startDate 开始日期时间
     * @param endDate 结束日期时间
     * @return 支付记录列表
     */
    List<Payment> findByDate(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * 根据日期范围统计支付记录数量
     * @param startDate 开始日期时间
     * @param endDate 结束日期时间
     * @return 记录数量
     */
    Long findByDateCount(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * 根据条件查询财务统计数据(按日聚合)
     * @param parkingLotId 停车场ID,可为空表示所有停车场
     * @param startDate 开始日期时间
     * @param endDate 结束日期时间
     * @return 每日统计数据列表,包含date、amount、count字段
     */
    List<Map<String, Object>> findDailyStatistics(@Param("parkingLotId") Long parkingLotId,
                                                   @Param("startDate") LocalDateTime startDate,
                                                   @Param("endDate") LocalDateTime endDate);

    /**
     * 根据条件查询财务统计数据(按周聚合)
     * @param parkingLotId 停车场ID,可为空表示所有停车场
     * @param startDate 开始日期时间
     * @param endDate 结束日期时间
     * @return 每周统计数据列表,包含date、amount、count字段
     */
    List<Map<String, Object>> findWeeklyStatistics(@Param("parkingLotId") Long parkingLotId,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate);

    /**
     * 根据条件查询财务统计数据(按月聚合)
     * @param parkingLotId 停车场ID,可为空表示所有停车场
     * @param startDate 开始日期时间
     * @param endDate 结束日期时间
     * @return 每月统计数据列表,包含date、amount、count字段
     */
    List<Map<String, Object>> findMonthlyStatistics(@Param("parkingLotId") Long parkingLotId,
                                                     @Param("startDate") LocalDateTime startDate,
                                                     @Param("endDate") LocalDateTime endDate);

    /**
     * 根据条件查询总金额
     * @param parkingLotId 停车场ID,可为空表示所有停车场
     * @param startDate 开始日期时间
     * @param endDate 结束日期时间
     * @return 总金额
     */
    BigDecimal findTotalAmount(@Param("parkingLotId") Long parkingLotId,
                               @Param("startDate") LocalDateTime startDate,
                               @Param("endDate") LocalDateTime endDate);

    /**
     * 根据条件查询总记录数
     * @param parkingLotId 停车场ID,可为空表示所有停车场
     * @param startDate 开始日期时间
     * @param endDate 结束日期时间
     * @return 总记录数
     */
    Long findTotalCount(@Param("parkingLotId") Long parkingLotId,
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);

    /**
     * 分页查询支付记录(带详细信息)
     * @param parkingLotId 停车场ID
     * @param plateNumber 车牌号
     * @param paymentMethod 支付方式
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param offset 偏移量
     * @param pageSize 每页数量
     * @return 支付记录详细信息列表
     */
    List<Map<String, Object>> findPaymentRecordsWithDetails(@Param("parkingLotId") Long parkingLotId,
                                                             @Param("plateNumber") String plateNumber,
                                                             @Param("paymentMethod") String paymentMethod,
                                                             @Param("startDate") LocalDateTime startDate,
                                                             @Param("endDate") LocalDateTime endDate,
                                                             @Param("offset") Integer offset,
                                                             @Param("pageSize") Integer pageSize);

    /**
     * 统计符合条件的支付记录总数
     * @param parkingLotId 停车场ID
     * @param plateNumber 车牌号
     * @param paymentMethod 支付方式
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 总记录数
     */
    Long countPaymentRecords(@Param("parkingLotId") Long parkingLotId,
                             @Param("plateNumber") String plateNumber,
                             @Param("paymentMethod") String paymentMethod,
                             @Param("startDate") LocalDateTime startDate,
                             @Param("endDate") LocalDateTime endDate);

    /**
     * 查询按小时统计的高峰时段数据
     * @param parkingLotId 停车场ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 每小时的订单数量统计
     */
    List<Map<String, Object>> findPeakHours(@Param("parkingLotId") Long parkingLotId,
                                            @Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);

    /**
     * 查询支付方式分布统计
     * @param parkingLotId 停车场ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 各支付方式的订单数量统计
     */
    List<Map<String, Object>> findPaymentMethodDistribution(@Param("parkingLotId") Long parkingLotId,
                                                             @Param("startDate") LocalDateTime startDate,
                                                             @Param("endDate") LocalDateTime endDate);

    /**
     * 统计不同车辆数(去重)
     * @param parkingLotId 停车场ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 车辆数量
     */
    Long countDistinctVehicles(@Param("parkingLotId") Long parkingLotId,
                               @Param("startDate") LocalDateTime startDate,
                               @Param("endDate") LocalDateTime endDate);
}
