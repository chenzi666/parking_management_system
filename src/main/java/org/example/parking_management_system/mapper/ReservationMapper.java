package org.example.parking_management_system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.parking_management_system.entity.Reservation;

import java.util.List;

@Mapper
public interface ReservationMapper {
    
    /**
     * 插入预约记录
     * @param reservation 预约实体
     * @return 影响行数
     */
    int insert(Reservation reservation);
    
    /**
     * 根据ID更新预约状态
     * @param id 预约ID
     * @param status 新状态
     * @return 影响行数
     */
    int updateStatusById(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 根据用户ID和状态查询预约列表
     * @param userId 用户ID
     * @param status 状态（可选）
     * @param offset 偏移量
     * @param limit 限制数量
     * @return 预约列表
     */
    List<Reservation> selectByUserIdAndStatus(@Param("userId") Long userId, 
                                              @Param("status") Integer status,
                                              @Param("offset") Integer offset,
                                              @Param("limit") Integer limit);
    
    /**
     * 根据用户ID和状态统计预约数量
     * @param userId 用户ID
     * @param status 状态（可选）
     * @return 预约数量
     */
    long countByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Integer status);
    
    /**
     * 根据ID查询预约详情
     * @param id 预约ID
     * @return 预约实体
     */
    Reservation selectById(@Param("id") Long id);
    
    /**
     * 根据用户ID和ID查询预约
     * @param userId 用户ID
     * @param id 预约ID
     * @return 预约实体
     */
    Reservation selectByUserIdAndId(@Param("userId") Long userId, @Param("id") Long id);
    
    /**
     * 检查指定时间段内是否有冲突的预约
     * @param parkingSpaceId 停车位ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param excludeId 排除的预约ID（用于更新时检查）
     * @return 冲突的预约数量
     */
    long countConflictingReservations(@Param("parkingSpaceId") Long parkingSpaceId,
                                     @Param("startTime") String startTime,
                                     @Param("endTime") String endTime,
                                     @Param("excludeId") Long excludeId);
}
