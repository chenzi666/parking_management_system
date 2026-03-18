-- 停车场管理系统数据库创建脚本

-- 创建数据库
CREATE DATABASE IF NOT EXISTS parking_management_system 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE parking_management_system;

-- 创建用户表
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(255) NOT NULL COMMENT '密码（加密存储）',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `balance` decimal(10,2) DEFAULT '0.00' COMMENT '账户余额',
  `membership_level` int DEFAULT '0' COMMENT '会员等级',
  `points` int DEFAULT '0' COMMENT '积分',
  `status` tinyint DEFAULT '1' COMMENT '状态：0禁用，1启用',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表 - 存储系统用户信息';

-- 创建管理员表
CREATE TABLE `admin_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码（加密存储）',
  `role` varchar(20) NOT NULL COMMENT '角色',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` tinyint DEFAULT '1' COMMENT '状态：0禁用，1启用',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员表 - 存储系统管理员信息';

-- 创建车辆信息表
CREATE TABLE `vehicle` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '车辆ID',
  `plate_number` varchar(20) NOT NULL COMMENT '车牌号',
  `user_id` bigint NOT NULL COMMENT '关联用户ID',
  `vehicle_type` varchar(20) DEFAULT 'car' COMMENT '车辆类型',
  `color` varchar(20) DEFAULT NULL COMMENT '车辆颜色',
  `is_default` tinyint DEFAULT '0' COMMENT '是否默认车辆：0否，1是',
  `is_special` tinyint DEFAULT '0' COMMENT '是否特殊车辆：0否，1是',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_plate_number` (`plate_number`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `fk_vehicle_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='车辆信息表 - 存储用户车辆信息';

-- 创建停车场表
CREATE TABLE `parking_lot` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '停车场ID',
  `name` varchar(100) NOT NULL COMMENT '停车场名称',
  `address` varchar(255) NOT NULL COMMENT '停车场地址',
  `total_spaces` int NOT NULL COMMENT '总车位数',
  `available_spaces` int DEFAULT '0' COMMENT '可用车位数',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `latitude` decimal(10,8) DEFAULT NULL COMMENT '纬度',
  `longitude` decimal(11,8) DEFAULT NULL COMMENT '经度',
  `description` text COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态：0关闭，1营业',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场表 - 存储停车场基本信息';

-- 创建月租车表
CREATE TABLE `monthly_rental` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '月租车ID',
  `vehicle_id` bigint NOT NULL COMMENT '关联车辆ID',
  `parking_lot_id` bigint NOT NULL COMMENT '关联停车场ID',
  `start_date` datetime NOT NULL COMMENT '开始日期',
  `end_date` datetime NOT NULL COMMENT '结束日期',
  `fee` decimal(10,2) NOT NULL COMMENT '费用',
  `payment_status` tinyint DEFAULT '0' COMMENT '支付状态：0未支付，1已支付',
  `status` tinyint DEFAULT '1' COMMENT '状态：0失效，1有效',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_vehicle_id` (`vehicle_id`),
  KEY `idx_parking_lot_id` (`parking_lot_id`),
  CONSTRAINT `fk_monthly_rental_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`),
  CONSTRAINT `fk_monthly_rental_parking_lot` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='月租车表 - 存储车辆包月信息';

-- 创建区域表
CREATE TABLE `area` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '区域ID',
  `parking_lot_id` bigint NOT NULL COMMENT '关联停车场ID',
  `name` varchar(50) NOT NULL COMMENT '区域名称',
  `description` text COMMENT '区域描述',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parking_lot_id` (`parking_lot_id`),
  CONSTRAINT `fk_area_parking_lot` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='区域表 - 存储停车场区域信息';

-- 创建车位信息表
CREATE TABLE `parking_space` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '车位ID',
  `parking_lot_id` bigint NOT NULL COMMENT '关联停车场ID',
  `area_id` bigint DEFAULT NULL COMMENT '关联区域ID',
  `space_number` varchar(20) NOT NULL COMMENT '车位编号',
  `space_type` varchar(20) DEFAULT 'normal' COMMENT '车位类型：normal普通，vip贵宾，disabled残疾人',
  `status` tinyint DEFAULT '0' COMMENT '状态：0空闲，1占用，2预约',
  `is_monthly` tinyint DEFAULT '0' COMMENT '是否月租车专用：0否，1是',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_parking_lot_space_number` (`parking_lot_id`,`space_number`),
  KEY `idx_parking_lot_id` (`parking_lot_id`),
  KEY `idx_area_id` (`area_id`),
  CONSTRAINT `fk_space_parking_lot` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`),
  CONSTRAINT `fk_space_area` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='车位信息表 - 存储车位详细信息';

-- 创建停车记录表
CREATE TABLE `parking_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '停车记录ID',
  `vehicle_id` bigint NOT NULL COMMENT '关联车辆ID',
  `parking_lot_id` bigint NOT NULL COMMENT '关联停车场ID',
  `parking_space_id` bigint NOT NULL COMMENT '关联车位ID',
  `entry_time` datetime NOT NULL COMMENT '入场时间',
  `exit_time` datetime DEFAULT NULL COMMENT '出场时间',
  `parking_duration` int DEFAULT NULL COMMENT '停车时长（分钟）',
  `total_fee` decimal(10,2) DEFAULT '0.00' COMMENT '总费用',
  `payment_status` tinyint DEFAULT '0' COMMENT '支付状态：0未支付，1已支付',
  `is_monthly` tinyint DEFAULT '0' COMMENT '是否月租车：0否，1是',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_vehicle_entry` (`vehicle_id`,`entry_time`),
  KEY `idx_parking_lot_entry` (`parking_lot_id`,`entry_time`),
  KEY `idx_parking_space_id` (`parking_space_id`),
  CONSTRAINT `fk_record_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`),
  CONSTRAINT `fk_record_parking_lot` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`),
  CONSTRAINT `fk_record_space` FOREIGN KEY (`parking_space_id`) REFERENCES `parking_space` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车记录表 - 存储车辆出入场记录';

-- 创建预约记录表
CREATE TABLE `reservation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '预约记录ID',
  `user_id` bigint NOT NULL COMMENT '关联用户ID',
  `vehicle_id` bigint NOT NULL COMMENT '关联车辆ID',
  `parking_lot_id` bigint NOT NULL COMMENT '关联停车场ID',
  `parking_space_id` bigint NOT NULL COMMENT '关联车位ID',
  `start_time` datetime NOT NULL COMMENT '预约开始时间',
  `end_time` datetime NOT NULL COMMENT '预约结束时间',
  `status` tinyint DEFAULT '0' COMMENT '状态：0待使用，1已使用，2已取消',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_status` (`user_id`,`status`),
  KEY `idx_space_time` (`parking_space_id`,`start_time`,`end_time`),
  CONSTRAINT `fk_reservation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_reservation_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`),
  CONSTRAINT `fk_reservation_parking_lot` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`),
  CONSTRAINT `fk_reservation_space` FOREIGN KEY (`parking_space_id`) REFERENCES `parking_space` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='预约记录表 - 存储车位预约信息';

-- 创建支付记录表
CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '支付记录ID',
  `user_id` bigint NOT NULL COMMENT '关联用户ID',
  `parking_record_id` bigint DEFAULT NULL COMMENT '关联停车记录ID',
  `monthly_rental_id` bigint DEFAULT NULL COMMENT '关联月租车ID',
  `amount` decimal(10,2) NOT NULL COMMENT '支付金额',
  `payment_method` varchar(20) NOT NULL COMMENT '支付方式',
  `transaction_id` varchar(100) DEFAULT NULL COMMENT '交易流水号',
  `status` tinyint DEFAULT '0' COMMENT '支付状态：0失败，1成功',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_parking_record_id` (`parking_record_id`),
  KEY `idx_transaction_id` (`transaction_id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_payment_record` FOREIGN KEY (`parking_record_id`) REFERENCES `parking_record` (`id`),
  CONSTRAINT `fk_payment_monthly` FOREIGN KEY (`monthly_rental_id`) REFERENCES `monthly_rental` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='支付记录表 - 存储支付交易信息';

-- 创建收费规则表
CREATE TABLE `fee_rule` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '规则ID',
  `parking_lot_id` bigint NOT NULL COMMENT '关联停车场ID',
  `name` varchar(50) NOT NULL COMMENT '规则名称',
  `rule_type` varchar(20) DEFAULT 'hourly' COMMENT '规则类型：hourly按时，monthly包月',
  `first_hour_fee` decimal(10,2) DEFAULT '0.00' COMMENT '首小时费用',
  `additional_hour_fee` decimal(10,2) DEFAULT '0.00' COMMENT '超时时长费用',
  `daily_max_fee` decimal(10,2) DEFAULT '0.00' COMMENT '每日最高费用',
  `monthly_fee` decimal(10,2) DEFAULT '0.00' COMMENT '包月费用',
  `start_time` time DEFAULT NULL COMMENT '规则生效开始时间',
  `end_time` time DEFAULT NULL COMMENT '规则生效结束时间',
  `is_default` tinyint DEFAULT '0' COMMENT '是否默认规则：0否，1是',
  `status` tinyint DEFAULT '1' COMMENT '状态：0禁用，1启用',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parking_lot_default` (`parking_lot_id`,`is_default`),
  CONSTRAINT `fk_rule_parking_lot` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收费规则表 - 存储停车收费规则';

-- 创建公告信息表
CREATE TABLE `announcement` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `title` varchar(100) NOT NULL COMMENT '公告标题',
  `content` text NOT NULL COMMENT '公告内容',
  `admin_id` bigint NOT NULL COMMENT '发布管理员ID',
  `is_top` tinyint DEFAULT '0' COMMENT '是否置顶：0否，1是',
  `status` tinyint DEFAULT '1' COMMENT '状态：0禁用，1启用',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_admin_id` (`admin_id`),
  CONSTRAINT `fk_announcement_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公告信息表 - 存储系统公告';

-- 创建操作日志表
CREATE TABLE `operation_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `admin_id` bigint NOT NULL COMMENT '操作管理员ID',
  `operation_type` varchar(50) NOT NULL COMMENT '操作类型',
  `operation_content` text NOT NULL COMMENT '操作内容',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `idx_admin_id` (`admin_id`),
  KEY `idx_created_at` (`created_at`),
  CONSTRAINT `fk_log_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表 - 存储管理员操作记录';

-- 创建视图
CREATE VIEW v_user_vehicle AS
SELECT
    u.id AS user_id,
    u.phone,
    u.nickname,
    v.id AS vehicle_id,
    v.plate_number,
    v.vehicle_type,
    v.color,
    v.is_default
FROM
    user u
JOIN
    vehicle v ON u.id = v.user_id;

CREATE VIEW v_parking_space_status AS
SELECT
    p.id AS parking_lot_id,
    p.name AS parking_lot_name,
    COUNT(s.id) AS total_spaces,
    SUM(CASE WHEN s.status = 0 THEN 1 ELSE 0 END) AS available_spaces,
    SUM(CASE WHEN s.status = 1 THEN 1 ELSE 0 END) AS occupied_spaces,
    SUM(CASE WHEN s.status = 2 THEN 1 ELSE 0 END) AS reserved_spaces
FROM
    parking_lot p
JOIN
    parking_space s ON p.id = s.parking_lot_id
GROUP BY
    p.id, p.name;

CREATE VIEW v_parking_record_detail AS
SELECT
    pr.id,
    v.plate_number,
    u.nickname,
    pl.name AS parking_lot_name,
    ps.space_number,
    pr.entry_time,
    pr.exit_time,
    pr.parking_duration,
    pr.total_fee,
    pr.payment_status,
    pr.created_at
FROM
    parking_record pr
JOIN
    vehicle v ON pr.vehicle_id = v.id
JOIN
    user u ON v.user_id = u.id
JOIN
    parking_lot pl ON pr.parking_lot_id = pl.id
JOIN
    parking_space ps ON pr.parking_space_id = ps.id;

-- 创建存储过程：计算停车费用
DELIMITER //
CREATE PROCEDURE calculate_parking_fee(
    IN parking_record_id BIGINT,
    IN exit_time_param DATETIME
)
BEGIN
    DECLARE entry_time_val DATETIME;
    DECLARE parking_lot_id_val BIGINT;
    DECLARE duration_minutes INT;
    DECLARE first_hour_fee_val DECIMAL(10,2);
    DECLARE additional_hour_fee_val DECIMAL(10,2);
    DECLARE daily_max_fee_val DECIMAL(10,2);
    DECLARE calculated_fee DECIMAL(10,2);
    DECLARE is_monthly_val TINYINT;
    
    -- 获取停车记录信息
    SELECT 
        pr.entry_time, 
        pr.parking_lot_id, 
        pr.is_monthly
    INTO 
        entry_time_val, 
        parking_lot_id_val,
        is_monthly_val
    FROM parking_record pr
    WHERE pr.id = parking_record_id;
    
    -- 如果是月租车，费用为0
    IF is_monthly_val = 1 THEN
        SET calculated_fee = 0.00;
    ELSE
        -- 计算停车时长（分钟）
        SET duration_minutes = TIMESTAMPDIFF(MINUTE, entry_time_val, exit_time_param);
        
        -- 获取收费规则
        SELECT 
            first_hour_fee, 
            additional_hour_fee, 
            daily_max_fee
        INTO 
            first_hour_fee_val, 
            additional_hour_fee_val, 
            daily_max_fee_val
        FROM fee_rule
        WHERE parking_lot_id = parking_lot_id_val AND is_default = 1 AND status = 1
        LIMIT 1;
        
        -- 计算费用
        IF duration_minutes <= 60 THEN
            SET calculated_fee = first_hour_fee_val;
        ELSE
            SET calculated_fee = first_hour_fee_val + CEIL((duration_minutes - 60) / 60) * additional_hour_fee_val;
        END IF;
        
        -- 检查每日最高费用限制
        IF daily_max_fee_val > 0 AND calculated_fee > daily_max_fee_val THEN
            SET calculated_fee = daily_max_fee_val;
        END IF;
    END IF;
    
    -- 更新停车记录
    UPDATE parking_record
    SET 
        exit_time = exit_time_param,
        parking_duration = duration_minutes,
        total_fee = calculated_fee
    WHERE id = parking_record_id;
    
    -- 返回计算结果
    SELECT calculated_fee AS fee;
END //
DELIMITER ;

-- 创建存储过程：更新车位状态
DELIMITER //
CREATE PROCEDURE update_space_status(
    IN space_id BIGINT,
    IN new_status TINYINT
)
BEGIN
    DECLARE parking_lot_id_val BIGINT;
    DECLARE current_status TINYINT;
    
    -- 获取车位信息
    SELECT 
        parking_lot_id, 
        status
    INTO 
        parking_lot_id_val, 
        current_status
    FROM parking_space
    WHERE id = space_id;
    
    -- 更新车位状态
    UPDATE parking_space
    SET status = new_status
    WHERE id = space_id;
    
    -- 更新停车场可用车位数量
    IF current_status = 0 AND new_status != 0 THEN
        -- 车位从空闲变为其他状态，可用车位减1
        UPDATE parking_lot
        SET available_spaces = available_spaces - 1
        WHERE id = parking_lot_id_val;
    ELSEIF current_status != 0 AND new_status = 0 THEN
        -- 车位从其他状态变为空闲，可用车位加1
        UPDATE parking_lot
        SET available_spaces = available_spaces + 1
        WHERE id = parking_lot_id_val;
    END IF;
    
    -- 返回更新后的状态
    SELECT new_status AS status;
END //
DELIMITER ;

-- 初始化数据
-- 插入默认管理员，密码为：123456（加密后）
INSERT INTO `admin_user` (`username`, `password`, `role`, `real_name`, `phone`, `status`) 
VALUES ('admin', '$2a$10$G3y8NtRk2u1QJtKZQjvY1e2XkD9q7r8f8Q9g8H8f7j7K8l7k6j5h4', 'admin', '系统管理员', '13800138000', 1);

-- 插入示例停车场
INSERT INTO `parking_lot` (`name`, `address`, `total_spaces`, `available_spaces`, `contact_phone`, `latitude`, `longitude`, `description`, `status`) 
VALUES ('中央广场停车场', '北京市朝阳区中央广场地下一层', 500, 500, '010-12345678', 39.9087, 116.3975, '24小时营业的大型地下停车场', 1);

-- 插入示例区域
INSERT INTO `area` (`parking_lot_id`, `name`, `description`) 
VALUES (1, 'A区', '靠近商场入口'),
       (1, 'B区', '靠近写字楼');

-- 插入示例车位
INSERT INTO `parking_space` (`parking_lot_id`, `area_id`, `space_number`, `space_type`, `status`, `is_monthly`) 
VALUES 
(1, 1, 'A001', 'normal', 0, 0),
(1, 1, 'A002', 'normal', 0, 0),
(1, 1, 'A003', 'vip', 0, 0),
(1, 2, 'B001', 'normal', 0, 0),
(1, 2, 'B002', 'disabled', 0, 0);

-- 插入示例收费规则
INSERT INTO `fee_rule` (`parking_lot_id`, `name`, `rule_type`, `first_hour_fee`, `additional_hour_fee`, `daily_max_fee`, `monthly_fee`, `is_default`, `status`) 
VALUES 
(1, '工作日收费规则', 'hourly', 10.00, 5.00, 80.00, 0.00, 1, 1),
(1, '包月收费', 'monthly', 0.00, 0.00, 0.00, 800.00, 0, 1);

-- 插入示例公告
INSERT INTO `announcement` (`title`, `content`, `admin_id`, `is_top`, `status`) 
VALUES ('停车场开业公告', '尊敬的车主您好，中央广场停车场将于2023年1月1日正式开业，开业期间停车享受5折优惠！', 1, 1, 1);

-- 显示创建成功信息
SELECT '停车场管理系统数据库创建成功！' AS message;