# 停车场管理系统API接口文档

## 1. 接口文档概述

### 1.1 文档说明
本文档基于停车场管理系统的需求分析和数据库设计，详细描述系统所有API接口的定义、参数、响应格式等信息，为前端和后端开发提供统一的接口规范。

### 1.2 接口架构
- **基础路径**：`/api/v1`
- **认证方式**：JWT Token认证
- **请求/响应格式**：JSON
- **状态码说明**：
  - `200`：请求成功
  - `400`：请求参数错误
  - `401`：未认证/认证过期
  - `403`：没有权限
  - `404`：资源不存在
  - `500`：服务器内部错误

### 1.3 目录结构
- [2. 车主用户端接口](#2-车主用户端接口)
  - [2.1 用户管理接口](#21-用户管理接口)
  - [2.2 车辆管理接口](#22-车辆管理接口)
  - [2.3 停车场查询接口](#23-停车场查询接口)
  - [2.4 预约管理接口](#24-预约管理接口)
  - [2.5 停车记录接口](#25-停车记录接口)
  - [2.6 支付相关接口](#26-支付相关接口)
  - [2.7 个人中心接口](#27-个人中心接口)
- [3. 管理端接口](#3-管理端接口)
  - [3.1 管理员认证接口](#31-管理员认证接口)
  - [3.2 车辆管理接口](#32-车辆管理接口)
  - [3.3 车位管理接口](#33-车位管理接口)
  - [3.4 停车场管理接口](#34-停车场管理接口)
  - [3.5 收费规则接口](#35-收费规则接口)
  - [3.6 财务统计接口](#36-财务统计接口)
  - [3.7 系统管理接口](#37-系统管理接口)
- [4. 通用接口](#4-通用接口)
  - [4.1 文件上传接口](#41-文件上传接口)
  - [4.2 公告查询接口](#42-公告查询接口)

## 2. 车主用户端接口

### 2.1 用户管理接口

#### 2.1.1 用户注册
- **接口路径**：`/api/v1/user/register`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "phone": "13800138000",
    "password": "123456",
    "code": "123456"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "注册成功",
    "data": {
      "id": 1,
      "phone": "13800138000",
      "nickname": null,
      "avatar": null,
      "balance": 0.00,
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
  }
  ```

#### 2.1.2 用户登录
- **接口路径**：`/api/v1/user/login`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "phone": "13800138000",
    "password": "123456"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "登录成功",
    "data": {
      "id": 1,
      "phone": "13800138000",
      "nickname": "张三",
      "avatar": "http://example.com/avatar.jpg",
      "balance": 100.00,
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
  }
  ```

#### 2.1.3 获取验证码
- **接口路径**：`/api/v1/user/send-code`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "phone": "13800138000"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "验证码已发送",
    "data": null
  }
  ```

#### 2.1.4 修改密码
- **接口路径**：`/api/v1/user/change-password`
- **请求方法**：`PUT`
- **请求参数**：
  ```json
  {
    "oldPassword": "123456",
    "newPassword": "654321"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "密码修改成功",
    "data": null
  }
  ```

#### 2.1.5 忘记密码
- **接口路径**：`/api/v1/user/reset-password`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "phone": "13800138000",
    "code": "123456",
    "newPassword": "654321"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "密码重置成功",
    "data": null
  }
  ```

### 2.2 车辆管理接口

#### 2.2.1 添加车辆
- **接口路径**：`/api/v1/vehicle`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "plateNumber": "京A12345",
    "vehicleType": "car",
    "color": "白色",
    "isDefault": true
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "车辆添加成功",
    "data": {
      "id": 1,
      "plateNumber": "京A12345",
      "vehicleType": "car",
      "color": "白色",
      "isDefault": true,
      "createdAt": "2023-01-01 12:00:00"
    }
  }
  ```

#### 2.2.2 删除车辆
- **接口路径**：`/api/v1/vehicle/{id}`
- **请求方法**：`DELETE`
- **路径参数**：
  - `id`: 车辆ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "车辆删除成功",
    "data": null
  }
  ```

#### 2.2.3 修改车辆信息
- **接口路径**：`/api/v1/vehicle/{id}`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 车辆ID
- **请求参数**：
  ```json
  {
    "vehicleType": "suv",
    "color": "黑色",
    "isDefault": false
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "车辆信息更新成功",
    "data": {
      "id": 1,
      "plateNumber": "京A12345",
      "vehicleType": "suv",
      "color": "黑色",
      "isDefault": false,
      "updatedAt": "2023-01-02 12:00:00"
    }
  }
  ```

#### 2.2.4 获取我的车辆列表
- **接口路径**：`/api/v1/vehicle`
- **请求方法**：`GET`
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": [
      {
        "id": 1,
        "plateNumber": "京A12345",
        "vehicleType": "car",
        "color": "白色",
        "isDefault": true,
        "createdAt": "2023-01-01 12:00:00"
      },
      {
        "id": 2,
        "plateNumber": "京B67890",
        "vehicleType": "suv",
        "color": "黑色",
        "isDefault": false,
        "createdAt": "2023-01-02 12:00:00"
      }
    ]
  }
  ```

#### 2.2.5 获取车辆详情
- **接口路径**：`/api/v1/vehicle/{id}`
- **请求方法**：`GET`
- **路径参数**：
  - `id`: 车辆ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "id": 1,
      "plateNumber": "京A12345",
      "vehicleType": "car",
      "color": "白色",
      "isDefault": true,
      "createdAt": "2023-01-01 12:00:00"
    }
  }
  ```

### 2.3 停车场查询接口

#### 2.3.1 获取附近停车场列表
- **接口路径**：`/api/v1/parking-lot/nearby`
- **请求方法**：`GET`
- **请求参数**：
  - `latitude`: 纬度（必需）
  - `longitude`: 经度（必需）
  - `radius`: 搜索半径（米，默认5000）
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "中央广场停车场",
          "address": "北京市朝阳区中央广场地下一层",
          "totalSpaces": 500,
          "availableSpaces": 450,
          "distance": 1200,
          "contactPhone": "010-12345678",
          "status": 1
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

#### 2.3.2 获取停车场详情
- **接口路径**：`/api/v1/parking-lot/{id}`
- **请求方法**：`GET`
- **路径参数**：
  - `id`: 停车场ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "id": 1,
      "name": "中央广场停车场",
      "address": "北京市朝阳区中央广场地下一层",
      "totalSpaces": 500,
      "availableSpaces": 450,
      "contactPhone": "010-12345678",
      "latitude": 39.9087,
      "longitude": 116.3975,
      "description": "24小时营业的大型地下停车场",
      "status": 1,
      "feeRules": [
        {
          "id": 1,
          "name": "工作日收费规则",
          "ruleType": "hourly",
          "firstHourFee": 10.00,
          "additionalHourFee": 5.00,
          "dailyMaxFee": 80.00
        }
      ]
    }
  }
  ```

#### 2.3.3 查询停车场车位状态
- **接口路径**：`/api/v1/parking-lot/{id}/space-status`
- **请求方法**：`GET`
- **路径参数**：
  - `id`: 停车场ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "totalSpaces": 500,
      "availableSpaces": 450,
      "occupiedSpaces": 45,
      "reservedSpaces": 5,
      "spaceByArea": [
        {
          "areaId": 1,
          "areaName": "A区",
          "totalSpaces": 200,
          "availableSpaces": 180
        },
        {
          "areaId": 2,
          "areaName": "B区",
          "totalSpaces": 300,
          "availableSpaces": 270
        }
      ]
    }
  }
  ```

### 2.4 预约管理接口

#### 2.4.1 创建预约
- **接口路径**：`/api/v1/reservation`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "vehicleId": 1,
    "parkingLotId": 1,
    "startTime": "2023-01-01 14:00:00",
    "endTime": "2023-01-01 18:00:00"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "预约成功",
    "data": {
      "id": 1,
      "parkingSpaceId": 10,
      "parkingSpaceNumber": "A010",
      "startTime": "2023-01-01 14:00:00",
      "endTime": "2023-01-01 18:00:00",
      "status": 0
    }
  }
  ```

#### 2.4.2 取消预约
- **接口路径**：`/api/v1/reservation/{id}/cancel`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 预约ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "预约取消成功",
    "data": null
  }
  ```

#### 2.4.3 获取我的预约列表
- **接口路径**：`/api/v1/reservation`
- **请求方法**：`GET`
- **请求参数**：
  - `status`: 预约状态（可选）
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "parkingLotName": "中央广场停车场",
          "parkingSpaceNumber": "A010",
          "plateNumber": "京A12345",
          "startTime": "2023-01-01 14:00:00",
          "endTime": "2023-01-01 18:00:00",
          "status": 0,
          "statusText": "待使用"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

#### 2.4.4 获取预约详情
- **接口路径**：`/api/v1/reservation/{id}`
- **请求方法**：`GET`
- **路径参数**：
  - `id`: 预约ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "id": 1,
      "parkingLotId": 1,
      "parkingLotName": "中央广场停车场",
      "parkingSpaceId": 10,
      "parkingSpaceNumber": "A010",
      "vehicleId": 1,
      "plateNumber": "京A12345",
      "startTime": "2023-01-01 14:00:00",
      "endTime": "2023-01-01 18:00:00",
      "status": 0,
      "statusText": "待使用",
      "createdAt": "2023-01-01 10:00:00"
    }
  }
  ```

### 2.5 停车记录接口

#### 2.5.1 获取我的停车记录
- **接口路径**：`/api/v1/parking-record`
- **请求方法**：`GET`
- **请求参数**：
  - `paymentStatus`: 支付状态（可选）
  - `startDate`: 开始日期（可选）
  - `endDate`: 结束日期（可选）
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "parkingLotName": "中央广场停车场",
          "plateNumber": "京A12345",
          "entryTime": "2023-01-01 12:00:00",
          "exitTime": "2023-01-01 14:30:00",
          "parkingDuration": 150,
          "totalFee": 20.00,
          "paymentStatus": 1,
          "paymentStatusText": "已支付"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

#### 2.5.2 获取停车记录详情
- **接口路径**：`/api/v1/parking-record/{id}`
- **请求方法**：`GET`
- **路径参数**：
  - `id`: 停车记录ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "id": 1,
      "parkingLotId": 1,
      "parkingLotName": "中央广场停车场",
      "parkingSpaceNumber": "A001",
      "vehicleId": 1,
      "plateNumber": "京A12345",
      "entryTime": "2023-01-01 12:00:00",
      "exitTime": "2023-01-01 14:30:00",
      "parkingDuration": 150,
      "totalFee": 20.00,
      "paymentStatus": 1,
      "paymentStatusText": "已支付",
      "isMonthly": false
    }
  }
  ```

### 2.6 支付相关接口

#### 2.6.1 创建支付订单
- **接口路径**：`/api/v1/payment`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "parkingRecordId": 1,
    "paymentMethod": "wechat"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "支付订单创建成功",
    "data": {
      "paymentId": 1,
      "amount": 20.00,
      "paymentMethod": "wechat",
      "payUrl": "https://pay.weixin.qq.com/xxx"
    }
  }
  ```

#### 2.6.2 查询支付状态
- **接口路径**：`/api/v1/payment/{id}/status`
- **请求方法**：`GET`
- **路径参数**：
  - `id`: 支付ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "paymentId": 1,
      "status": 1,
      "statusText": "支付成功",
      "transactionId": "WX1234567890"
    }
  }
  ```

#### 2.6.3 余额支付
- **接口路径**：`/api/v1/payment/balance`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "parkingRecordId": 1
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "余额支付成功",
    "data": {
      "paymentId": 1,
      "amount": 20.00,
      "balance": 80.00,
      "status": 1
    }
  }
  ```

#### 2.6.4 申请发票
- **接口路径**：`/api/v1/payment/{id}/invoice`
- **请求方法**：`POST`
- **路径参数**：
  - `id`: 支付ID
- **请求参数**：
  ```json
  {
    "title": "个人",
    "taxNumber": "",
    "email": "user@example.com",
    "address": "北京市朝阳区"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "发票申请成功",
    "data": {
      "invoiceId": 1,
      "invoiceNo": "FP1234567890",
      "status": "processing"
    }
  }
  ```

### 2.7 个人中心接口

#### 2.7.1 获取个人信息
- **接口路径**：`/api/v1/user/profile`
- **请求方法**：`GET`
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "id": 1,
      "phone": "13800138000",
      "nickname": "张三",
      "avatar": "http://example.com/avatar.jpg",
      "balance": 100.00,
      "membershipLevel": 2,
      "points": 500,
      "createdAt": "2023-01-01 10:00:00"
    }
  }
  ```

#### 2.7.2 更新个人信息
- **接口路径**：`/api/v1/user/profile`
- **请求方法**：`PUT`
- **请求参数**：
  ```json
  {
    "nickname": "李四",
    "avatar": "http://example.com/new_avatar.jpg"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "个人信息更新成功",
    "data": {
      "id": 1,
      "phone": "13800138000",
      "nickname": "李四",
      "avatar": "http://example.com/new_avatar.jpg"
    }
  }
  ```

#### 2.7.3 余额充值
- **接口路径**：`/api/v1/user/recharge`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "amount": 100.00,
    "paymentMethod": "wechat"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "充值订单创建成功",
    "data": {
      "rechargeId": 1,
      "amount": 100.00,
      "payUrl": "https://pay.weixin.qq.com/xxx"
    }
  }
  ```

#### 2.7.4 获取余额明细
- **接口路径**：`/api/v1/user/balance-log`
- **请求方法**：`GET`
- **请求参数**：
  - `type`: 类型（recharge:充值, consume:消费）
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "amount": 100.00,
          "type": "recharge",
          "typeText": "充值",
          "balance": 200.00,
          "description": "微信充值",
          "createdAt": "2023-01-01 10:00:00"
        },
        {
          "id": 2,
          "amount": -20.00,
          "type": "consume",
          "typeText": "消费",
          "balance": 180.00,
          "description": "停车费",
          "createdAt": "2023-01-01 14:30:00"
        }
      ],
      "total": 2,
      "page": 1,
      "pageSize": 10
    }
  }
  }
  ```

## 3. 管理端接口

### 3.1 管理员认证接口

#### 3.1.1 管理员登录
- **接口路径**：`/api/v1/admin/login`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "username": "admin",
    "password": "123456"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "登录成功",
    "data": {
      "id": 1,
      "username": "admin",
      "realName": "系统管理员",
      "role": "admin",
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
  }
  ```

#### 3.1.2 管理员退出
- **接口路径**：`/api/v1/admin/logout`
- **请求方法**：`POST`
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "退出成功",
    "data": null
  }
  ```

#### 3.1.3 获取当前管理员信息
- **接口路径**：`/api/v1/admin/profile`
- **请求方法**：`GET`
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "id": 1,
      "username": "admin",
      "realName": "系统管理员",
      "role": "admin",
      "phone": "13800138000",
      "status": 1
    }
  }
  ```

### 3.2 车辆管理接口

#### 3.2.1 获取所有车辆列表
- **接口路径**：`/api/v1/admin/vehicle`
- **请求方法**：`GET`
- **请求参数**：
  - `plateNumber`: 车牌号（模糊搜索）
  - `phone`: 车主手机号
  - `status`: 状态
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "plateNumber": "京A12345",
          "userName": "张三",
          "phone": "13800138000",
          "vehicleType": "car",
          "color": "白色",
          "isSpecial": false,
          "createdAt": "2023-01-01 12:00:00"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

#### 3.2.2 获取车辆详情
- **接口路径**：`/api/v1/admin/vehicle/{id}`
- **请求方法**：`GET`
- **路径参数**：
  - `id`: 车辆ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "id": 1,
      "plateNumber": "京A12345",
      "userId": 1,
      "userName": "张三",
      "phone": "13800138000",
      "vehicleType": "car",
      "color": "白色",
      "isDefault": true,
      "isSpecial": false,
      "createdAt": "2023-01-01 12:00:00",
      "monthlyInfo": null
    }
  }
  ```

#### 3.2.3 更新车辆信息
- **接口路径**：`/api/v1/admin/vehicle/{id}`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 车辆ID
- **请求参数**：
  ```json
  {
    "vehicleType": "suv",
    "color": "黑色",
    "isSpecial": true
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "车辆信息更新成功",
    "data": {
      "id": 1,
      "plateNumber": "京A12345",
      "vehicleType": "suv",
      "color": "黑色",
      "isSpecial": true,
      "updatedAt": "2023-01-02 12:00:00"
    }
  }
  ```

#### 3.2.4 批量导入车辆
- **接口路径**：`/api/v1/admin/vehicle/import`
- **请求方法**：`POST`
- **请求参数**：文件上传（Excel格式）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "导入成功",
    "data": {
      "total": 10,
      "success": 8,
      "fail": 2,
      "failList": ["京B12345: 车牌号已存在", "京C12345: 格式错误"]
    }
  }
  ```

### 3.3 车位管理接口

#### 3.3.1 获取车位列表
- **接口路径**：`/api/v1/admin/parking-space`
- **请求方法**：`GET`
- **请求参数**：
  - `parkingLotId`: 停车场ID
  - `areaId`: 区域ID
  - `spaceNumber`: 车位编号
  - `spaceType`: 车位类型
  - `status`: 状态
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "parkingLotId": 1,
          "parkingLotName": "中央广场停车场",
          "areaId": 1,
          "areaName": "A区",
          "spaceNumber": "A001",
          "spaceType": "normal",
          "spaceTypeText": "普通车位",
          "status": 0,
          "statusText": "空闲",
          "isMonthly": false,
          "createdAt": "2023-01-01 10:00:00"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  }
  ```

#### 3.3.2 新增车位
- **接口路径**：`/api/v1/admin/parking-space`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "parkingLotId": 1,
    "areaId": 1,
    "spaceNumber": "A006",
    "spaceType": "normal",
    "isMonthly": false
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "车位添加成功",
    "data": {
      "id": 6,
      "parkingLotId": 1,
      "areaId": 1,
      "spaceNumber": "A006",
      "spaceType": "normal",
      "status": 0,
      "isMonthly": false
    }
  }
  ```

#### 3.3.3 批量新增车位
- **接口路径**：`/api/v1/admin/parking-space/batch`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "parkingLotId": 1,
    "areaId": 1,
    "startNumber": "A010",
    "endNumber": "A020",
    "spaceType": "normal",
    "isMonthly": false
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "批量添加成功",
    "data": {
      "count": 11
    }
  }
  ```

#### 3.3.4 更新车位信息
- **接口路径**：`/api/v1/admin/parking-space/{id}`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 车位ID
- **请求参数**：
  ```json
  {
    "areaId": 2,
    "spaceNumber": "B006",
    "spaceType": "vip",
    "status": 0,
    "isMonthly": true
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "车位信息更新成功",
    "data": {
      "id": 1,
      "areaId": 2,
      "spaceNumber": "B006",
      "spaceType": "vip",
      "status": 0,
      "isMonthly": true
    }
  }
  ```

#### 3.3.5 删除车位
- **接口路径**：`/api/v1/admin/parking-space/{id}`
- **请求方法**：`DELETE`
- **路径参数**：
  - `id`: 车位ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "车位删除成功",
    "data": null
  }
  ```

### 3.4 停车场管理接口

#### 3.4.1 获取停车场列表
- **接口路径**：`/api/v1/admin/parking-lot`
- **请求方法**：`GET`
- **请求参数**：
  - `name`: 停车场名称
  - `status`: 状态
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "中央广场停车场",
          "address": "北京市朝阳区中央广场地下一层",
          "totalSpaces": 500,
          "availableSpaces": 450,
          "contactPhone": "010-12345678",
          "status": 1,
          "statusText": "开放",
          "createdAt": "2023-01-01 10:00:00"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  }
  ```

#### 3.4.2 新增停车场
- **接口路径**：`/api/v1/admin/parking-lot`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "name": "王府井停车场",
    "address": "北京市东城区王府井大街1号",
    "totalSpaces": 300,
    "contactPhone": "010-87654321",
    "latitude": 39.9143,
    "longitude": 116.4038,
    "description": "市中心大型停车场",
    "status": 1
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "停车场添加成功",
    "data": {
      "id": 2,
      "name": "王府井停车场",
      "totalSpaces": 300,
      "availableSpaces": 300,
      "status": 1
    }
  }
  ```

#### 3.4.3 更新停车场信息
- **接口路径**：`/api/v1/admin/parking-lot/{id}`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 停车场ID
- **请求参数**：
  ```json
  {
    "contactPhone": "010-11112222",
    "description": "24小时营业的大型地下停车场，提供充电桩服务",
    "status": 1
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "停车场信息更新成功",
    "data": {
      "id": 1,
      "contactPhone": "010-11112222",
      "description": "24小时营业的大型地下停车场，提供充电桩服务"
    }
  }
  ```

#### 3.4.4 删除停车场
- **接口路径**：`/api/v1/admin/parking-lot/{id}`
- **请求方法**：`DELETE`
- **路径参数**：
  - `id`: 停车场ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "停车场删除成功",
    "data": null
  }
  ```

#### 3.4.5 管理区域

##### 3.4.5.1 获取区域列表
- **接口路径**：`/api/v1/admin/parking-lot/{id}/area`
- **请求方法**：`GET`
- **路径参数**：
  - `id`: 停车场ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": [
      {
        "id": 1,
        "name": "A区",
        "description": "靠近商场入口"
      },
      {
        "id": 2,
        "name": "B区",
        "description": "靠近写字楼"
      }
    ]
  }
  ```

##### 3.4.5.2 新增区域
- **接口路径**：`/api/v1/admin/parking-lot/{id}/area`
- **请求方法**：`POST`
- **路径参数**：
  - `id`: 停车场ID
- **请求参数**：
  ```json
  {
    "name": "C区",
    "description": "靠近出口"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "区域添加成功",
    "data": {
      "id": 3,
      "name": "C区",
      "description": "靠近出口"
    }
  }
  ```

##### 3.4.5.3 更新区域
- **接口路径**：`/api/v1/admin/area/{id}`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 区域ID
- **请求参数**：
  ```json
  {
    "name": "C区（快速通道）",
    "description": "靠近出口，快速通道"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "区域更新成功",
    "data": {
      "id": 3,
      "name": "C区（快速通道）",
      "description": "靠近出口，快速通道"
    }
  }
  ```

##### 3.4.5.4 删除区域
- **接口路径**：`/api/v1/admin/area/{id}`
- **请求方法**：`DELETE`
- **路径参数**：
  - `id`: 区域ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "区域删除成功",
    "data": null
  }
  ```

### 3.5 收费规则接口

#### 3.5.1 获取收费规则列表
- **接口路径**：`/api/v1/admin/fee-rule`
- **请求方法**：`GET`
- **请求参数**：
  - `parkingLotId`: 停车场ID
  - `ruleType`: 规则类型
  - `status`: 状态
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": [
      {
        "id": 1,
        "parkingLotId": 1,
        "parkingLotName": "中央广场停车场",
        "name": "工作日收费规则",
        "ruleType": "hourly",
        "ruleTypeText": "按时收费",
        "firstHourFee": 10.00,
        "additionalHourFee": 5.00,
        "dailyMaxFee": 80.00,
        "isDefault": true,
        "status": 1,
        "statusText": "启用"
      },
      {
        "id": 2,
        "parkingLotId": 1,
        "parkingLotName": "中央广场停车场",
        "name": "包月收费",
        "ruleType": "monthly",
        "ruleTypeText": "包月收费",
        "monthlyFee": 800.00,
        "isDefault": false,
        "status": 1,
        "statusText": "启用"
      }
    ]
  }
  ```

#### 3.5.2 新增收费规则
- **接口路径**：`/api/v1/admin/fee-rule`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "parkingLotId": 1,
    "name": "周末收费规则",
    "ruleType": "hourly",
    "firstHourFee": 8.00,
    "additionalHourFee": 4.00,
    "dailyMaxFee": 60.00,
    "startTime": "08:00:00",
    "endTime": "20:00:00",
    "isDefault": false,
    "status": 1
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "收费规则添加成功",
    "data": {
      "id": 3,
      "name": "周末收费规则",
      "ruleType": "hourly",
      "firstHourFee": 8.00,
      "additionalHourFee": 4.00,
      "dailyMaxFee": 60.00,
      "isDefault": false,
      "status": 1
    }
  }
  ```

#### 3.5.3 更新收费规则
- **接口路径**：`/api/v1/admin/fee-rule/{id}`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 规则ID
- **请求参数**：
  ```json
  {
    "firstHourFee": 12.00,
    "additionalHourFee": 6.00,
    "dailyMaxFee": 100.00
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "收费规则更新成功",
    "data": {
      "id": 1,
      "firstHourFee": 12.00,
      "additionalHourFee": 6.00,
      "dailyMaxFee": 100.00
    }
  }
  ```

#### 3.5.4 设置默认规则
- **接口路径**：`/api/v1/admin/fee-rule/{id}/default`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 规则ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "设置默认规则成功",
    "data": null
  }
  ```

#### 3.5.5 删除收费规则
- **接口路径**：`/api/v1/admin/fee-rule/{id}`
- **请求方法**：`DELETE`
- **路径参数**：
  - `id`: 规则ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "收费规则删除成功",
    "data": null
  }
  ```

### 3.6 财务统计接口

#### 3.6.1 获取收费统计
- **接口路径**：`/api/v1/admin/finance/statistics`
- **请求方法**：`GET`
- **请求参数**：
  - `parkingLotId`: 停车场ID（可选）
  - `startDate`: 开始日期（必选）
  - `endDate`: 结束日期（必选）
  - `type`: 统计类型（day:按天, week:按周, month:按月）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "totalAmount": 15000.00,
      "totalCount": 500,
      "averageAmount": 30.00,
      "chartData": [
        {
          "date": "2023-01-01",
          "amount": 2000.00,
          "count": 50
        },
        {
          "date": "2023-01-02",
          "amount": 1800.00,
          "count": 45
        }
      ]
    }
  }
  ```

#### 3.6.2 获取收费记录
- **接口路径**：`/api/v1/admin/finance/payment-records`
- **请求方法**：`GET`
- **请求参数**：
  - `parkingLotId`: 停车场ID
  - `plateNumber`: 车牌号
  - `paymentMethod`: 支付方式
  - `startDate`: 开始日期
  - `endDate`: 结束日期
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "parkingLotName": "中央广场停车场",
          "plateNumber": "京A12345",
          "amount": 20.00,
          "paymentMethod": "wechat",
          "paymentMethodText": "微信支付",
          "transactionId": "WX1234567890",
          "createdAt": "2023-01-01 14:30:00"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  }
  ```

#### 3.6.3 导出收费记录
- **接口路径**：`/api/v1/admin/finance/export`
- **请求方法**：`GET`
- **请求参数**：
  - `parkingLotId`: 停车场ID
  - `startDate`: 开始日期
  - `endDate`: 结束日期
- **响应**：Excel文件下载

#### 3.6.4 获取运营数据看板
- **接口路径**：`/api/v1/admin/dashboard`
- **请求方法**：`GET`
- **请求参数**：
  - `parkingLotId`: 停车场ID（可选）
  - `dateType`: 日期类型（today:今天, week:本周, month:本月）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "totalRevenue": 15000.00,
      "totalOrders": 500,
      "totalVehicles": 800,
      "averageOccupancyRate": 0.85,
      "peakHours": [
        {"hour": "12", "count": 80},
        {"hour": "18", "count": 95}
      ],
      "paymentMethodDistribution": [
        {"method": "wechat", "count": 300, "percentage": 60},
        {"method": "alipay", "count": 150, "percentage": 30},
        {"method": "balance", "count": 50, "percentage": 10}
      ]
    }
  }
  ```

### 3.7 系统管理接口

#### 3.7.1 管理员用户管理

##### 3.7.1.1 获取管理员列表
- **接口路径**：`/api/v1/admin/user`
- **请求方法**：`GET`
- **请求参数**：
  - `username`: 用户名
  - `role`: 角色
  - `status`: 状态
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "username": "admin",
          "realName": "系统管理员",
          "role": "admin",
          "roleText": "超级管理员",
          "phone": "13800138000",
          "status": 1,
          "statusText": "启用",
          "createdAt": "2023-01-01 10:00:00"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  }
  ```

##### 3.7.1.2 新增管理员
- **接口路径**：`/api/v1/admin/user`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "username": "operator1",
    "password": "123456",
    "realName": "操作员小王",
    "role": "operator",
    "phone": "13900139000",
    "status": 1
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "管理员添加成功",
    "data": {
      "id": 2,
      "username": "operator1",
      "realName": "操作员小王",
      "role": "operator",
      "status": 1
    }
  }
  ```

##### 3.7.1.3 更新管理员信息
- **接口路径**：`/api/v1/admin/user/{id}`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 管理员ID
- **请求参数**：
  ```json
  {
    "realName": "操作员大王",
    "phone": "13900139001",
    "status": 1
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "管理员信息更新成功",
    "data": {
      "id": 2,
      "realName": "操作员大王",
      "phone": "13900139001",
      "status": 1
    }
  }
  ```

##### 3.7.1.4 修改管理员密码
- **接口路径**：`/api/v1/admin/user/{id}/password`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 管理员ID
- **请求参数**：
  ```json
  {
    "password": "654321"
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "密码修改成功",
    "data": null
  }
  ```

##### 3.7.1.5 删除管理员
- **接口路径**：`/api/v1/admin/user/{id}`
- **请求方法**：`DELETE`
- **路径参数**：
  - `id`: 管理员ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "管理员删除成功",
    "data": null
  }
  ```

#### 3.7.2 公告管理

##### 3.7.2.1 获取公告列表
- **接口路径**：`/api/v1/admin/announcement`
- **请求方法**：`GET`
- **请求参数**：
  - `title`: 标题
  - `status`: 状态
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "title": "停车场开业公告",
          "content": "尊敬的车主您好，中央广场停车场将于2023年1月1日正式开业...",
          "adminName": "系统管理员",
          "isTop": true,
          "status": 1,
          "statusText": "发布",
          "createdAt": "2023-01-01 10:00:00"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  }
  ```

##### 3.7.2.2 新增公告
- **接口路径**：`/api/v1/admin/announcement`
- **请求方法**：`POST`
- **请求参数**：
  ```json
  {
    "title": "春节期间营业时间调整",
    "content": "尊敬的车主您好，春节期间（1月21日-1月27日）停车场营业时间调整为8:00-20:00...",
    "isTop": true,
    "status": 1
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "公告发布成功",
    "data": {
      "id": 2,
      "title": "春节期间营业时间调整",
      "isTop": true,
      "status": 1
    }
  }
  ```

##### 3.7.2.3 更新公告
- **接口路径**：`/api/v1/admin/announcement/{id}`
- **请求方法**：`PUT`
- **路径参数**：
  - `id`: 公告ID
- **请求参数**：
  ```json
  {
    "title": "春节期间营业时间调整通知",
    "content": "尊敬的车主您好，春节期间（1月21日-1月27日）停车场营业时间调整为8:00-20:00，给您带来不便敬请谅解。",
    "isTop": false,
    "status": 1
  }
  ```
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "公告更新成功",
    "data": {
      "id": 2,
      "title": "春节期间营业时间调整通知",
      "isTop": false,
      "status": 1
    }
  }
  ```

##### 3.7.2.4 删除公告
- **接口路径**：`/api/v1/admin/announcement/{id}`
- **请求方法**：`DELETE`
- **路径参数**：
  - `id`: 公告ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "公告删除成功",
    "data": null
  }
  ```

#### 3.7.3 操作日志查询
- **接口路径**：`/api/v1/admin/logs`
- **请求方法**：`GET`
- **请求参数**：
  - `adminId`: 管理员ID
  - `operationType`: 操作类型
  - `startDate`: 开始日期
  - `endDate`: 结束日期
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "adminName": "系统管理员",
          "operationType": "login",
          "operationTypeText": "登录",
          "operationContent": "管理员登录成功",
          "ipAddress": "127.0.0.1",
          "createdAt": "2023-01-01 10:00:00"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  }
  ```

## 4. 通用接口

### 4.1 文件上传接口

#### 4.1.1 上传图片
- **接口路径**：`/api/v1/upload/image`
- **请求方法**：`POST`
- **请求参数**：文件上传（multipart/form-data）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "上传成功",
    "data": {
      "url": "http://example.com/uploads/20230101/abc123.jpg",
      "fileName": "avatar.jpg"
    }
  }
  ```

#### 4.1.2 上传文件
- **接口路径**：`/api/v1/upload/file`
- **请求方法**：`POST`
- **请求参数**：文件上传（multipart/form-data）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "上传成功",
    "data": {
      "url": "http://example.com/uploads/20230101/def456.xlsx",
      "fileName": "车辆数据.xlsx"
    }
  }
  ```

### 4.2 公告查询接口

#### 4.2.1 获取公告列表
- **接口路径**：`/api/v1/announcement`
- **请求方法**：`GET`
- **请求参数**：
  - `page`: 页码（默认1）
  - `pageSize`: 每页数量（默认10）
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "list": [
        {
          "id": 1,
          "title": "停车场开业公告",
          "content": "尊敬的车主您好，中央广场停车场将于2023年1月1日正式开业...",
          "isTop": true,
          "createdAt": "2023-01-01 10:00:00"
        }
      ],
      "total": 1,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

#### 4.2.2 获取公告详情
- **接口路径**：`/api/v1/announcement/{id}`
- **请求方法**：`GET`
- **路径参数**：
  - `id`: 公告ID
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "id": 1,
      "title": "停车场开业公告",
      "content": "尊敬的车主您好，中央广场停车场将于2023年1月1日正式开业。开业期间，首小时免费停车，欢迎广大车主前来体验。",
      "isTop": true,
      "createdAt": "2023-01-01 10:00:00"
    }
  }
  ```

### 4.3 获取系统配置

#### 4.3.1 获取系统基本配置
- **接口路径**：`/api/v1/config`
- **请求方法**：`GET`
- **响应示例**：
  ```json
  {
    "code": 200,
    "message": "查询成功",
    "data": {
      "appName": "智慧停车管理系统",
      "version": "1.0.0",
      "servicePhone": "400-123-4567",
      "supportEmail": "support@example.com",
      "workingHours": "24小时营业",
      "privacyPolicyUrl": "http://example.com/privacy",
      "userAgreementUrl": "http://example.com/agreement"
    }
  }
  ```

## 5. 接口认证与安全

### 5.1 JWT Token认证流程
1. 客户端通过登录接口获取Token
2. 后续请求在HTTP请求头中添加 `Authorization: Bearer {token}`
3. 服务端验证Token有效性，无效返回401错误
4. Token过期时间：用户Token默认2小时，管理员Token默认8小时

### 5.2 安全措施
- 所有密码使用BCrypt加密存储
- 敏感接口添加频率限制，防止暴力攻击
- 数据传输使用HTTPS加密
- SQL注入防护
- XSS攻击防护
- CSRF防护

### 5.3 请求频率限制
- 登录/注册接口：同一IP每分钟最多5次
- 获取验证码接口：同一手机号每分钟最多1次
- 支付接口：同一用户每秒最多1次
- 其他接口：同一IP每分钟最多60次

## 6. 错误码说明

| 错误码 | 描述 | 解决方案 |
|-------|------|--------|
| 40000 | 请求参数错误 | 检查请求参数格式和内容是否正确 |
| 40001 | 手机号格式错误 | 请输入正确的手机号格式 |
| 40002 | 验证码错误 | 请输入正确的验证码 |
| 40003 | 验证码已过期 | 请重新获取验证码 |
| 40004 | 密码错误 | 请输入正确的密码 |
| 40100 | 未授权访问 | 请先登录获取Token |
| 40101 | Token已过期 | 请重新登录获取Token |
| 40102 | Token无效 | 请确认Token是否正确 |
| 40300 | 没有权限操作 | 请联系管理员授权 |
| 40400 | 资源不存在 | 检查请求的资源ID是否正确 |
| 50000 | 服务器内部错误 | 系统异常，请稍后重试 |
| 50001 | 数据库操作失败 | 系统异常，请稍后重试 |
| 50002 | 第三方服务异常 | 支付服务不可用，请稍后重试 |
| 50003 | 文件上传失败 | 请检查文件大小和格式 |

## 7. 附录

### 7.1 数据类型说明
- `String`: 字符串类型
- `Integer`: 整数类型
- `Long`: 长整型
- `Double`: 双精度浮点型
- `Boolean`: 布尔类型，true/false
- `Date`: 日期时间类型，格式：yyyy-MM-dd HH:mm:ss
- `Array`: 数组类型
- `Object`: 对象类型

### 7.2 通用状态码含义
- **用户状态**：0-未激活 1-激活 2-禁用
- **车辆状态**：0-正常 1-特殊车辆 2-黑名单
- **车位状态**：0-空闲 1-占用 2-维护中
- **预约状态**：0-待使用 1-已使用 2-已取消 3-已过期
- **支付状态**：0-待支付 1-已支付 2-已退款 3-支付失败
- **停车场状态**：0-未开放 1-开放 2-暂停营业
- **公告状态**：0-草稿 1-发布 2-下架

### 7.3 接口调用示例（JavaScript）
```javascript
// 基础请求函数
async function request(url, options = {}) {
  const token = localStorage.getItem('token');
  
  const defaultOptions = {
    headers: {
      'Content-Type': 'application/json',
      ...(token && { Authorization: `Bearer ${token}` })
    }
  };
  
  const mergedOptions = {
    ...defaultOptions,
    ...options,
    headers: {
      ...defaultOptions.headers,
      ...options.headers
    }
  };
  
  try {
    const response = await fetch(`/api/v1${url}`, mergedOptions);
    
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    
    const data = await response.json();
    
    if (data.code !== 200) {
      throw new Error(data.message || '请求失败');
    }
    
    return data;
  } catch (error) {
    console.error('请求错误:', error);
    throw error;
  }
}

// 登录示例
async function login(phone, password) {
  return await request('/user/login', {
    method: 'POST',
    body: JSON.stringify({ phone, password })
  });
}

// 获取附近停车场示例
async function getNearbyParkingLots(latitude, longitude) {
  return await request(`/parking-lot/nearby?latitude=${latitude}&longitude=${longitude}`);
}
```

### 7.4 服务端技术栈
- **后端框架**：Spring Boot 2.7.x
- **数据库**：MySQL 8.0
- **缓存**：Redis 6.0
- **认证**：JWT
- **ORM框架**：MyBatis-Plus
- **API文档**：Swagger 3.0
- **日志**：Logback
- **安全框架**：Spring Security

### 7.5 文档版本信息
- **文档版本**：1.0.0
- **创建日期**：2023-01-01
- **最后更新**：2023-01-01
- **文档作者**：开发团队

---

*本文档为停车场管理系统API接口规范，仅供开发使用。如有接口变更，请及时更新本文档。*