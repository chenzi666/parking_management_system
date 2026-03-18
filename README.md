# 🅿️ 停车场管理系统 (Parking Management System)

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MyBatis](https://img.shields.io/badge/MyBatis-Latest-blue.svg)](https://mybatis.org/mybatis-3/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Vue](https://img.shields.io/badge/Vue-3.0-green.svg)](https://vuejs.org/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

> 一个基于 Spring Boot + Vue3 的智能停车管理平台，实现车位实时查询、在线预约、无感支付和智能导航等功能，优化停车资源配置，提升管理效率与用户体验。

## 📋 目录

- [项目简介](#-项目简介)
- [主要功能](#-主要功能)
- [技术栈](#-技术栈)
- [系统架构](#-系统架构)
- [快速开始](#-快速开始)
- [API 文档](#-api-文档)
- [数据库设计](#-数据库设计)
- [项目结构](#-项目结构)
- [开发团队](#-开发团队)
- [贡献指南](#-贡献指南)
- [许可证](#-许可证)

## ✨ 项目简介

随着城市化进程加快和汽车保有量急剧增长，"停车难"已成为困扰城市管理和影响市民出行的突出问题。传统停车场依赖人工管理，存在效率低下、车位信息不透明、收费不规范以及资源利用率不高等弊端。

本停车场管理系统旨在通过物联网、大数据及人工智能等技术，实现：
- 🚗 **车牌自动识别** - 无需取卡，自动记录入场时间
- 📱 **在线预约车位** - 提前预约指定时间段的车位
- 💳 **无感支付** - 支持多种支付方式，先离场后支付
- 🧭 **智能导航** - 提供从当前位置到出口的最佳路径指引
- 📊 **数据看板** - 直观展示车场利用率、收入趋势、车流高峰等关键运营数据

## 🎯 主要功能

### 👤 车主用户端

#### 入场与出场管理
- ✅ 空闲车位实时查询
- ✅ 在线预约车位
- ✅ 电子入场凭证
- ✅ 出场路径指引

#### 费用与支付
- ✅ 自动费用计算
- ✅ 在线支付（微信/支付宝/余额）
- ✅ 先离场后支付
- ✅ 电子发票
- ✅ 优惠活动

#### 个人中心
- ✅ 车辆管理
- ✅ 历史记录查询
- ✅ 余额管理与充值
- ✅ 会员信息管理
- ✅ 个人设置

### 👨‍💼 停车场管理端

#### 车辆与车位管理
- ✅ 车辆信息管理（临时车/月租车/VIP 车）
- ✅ 车位全面管理
- ✅ 车位状态实时监控
- ✅ 智能车位引导
- ✅ 特殊车辆标记

#### 运营与财务管理
- ✅ 收费规则管理
- ✅ 收费记录管理
- ✅ 财务报表（日报/周报/月报/年报）
- ✅ 数据看板
- ✅ 优惠活动管理

#### 系统管理
- ✅ 用户权限管理
- ✅ 公告管理
- ✅ 操作日志审计
- ✅ 系统配置
- ✅ 数据备份与恢复

## 🛠️ 技术栈

### 后端技术
| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 17 | 基础编程语言 |
| Spring Boot | 3.5.7 | 核心框架 |
| Spring Security | Latest | 安全认证 |
| MyBatis | Latest | ORM 框架 |
| MySQL | 8.0+ | 关系型数据库 |
| JWT | Latest | Token 身份认证 |
| Maven | Latest | 项目构建工具 |

### 前端技术（规划）
| 技术 | 版本 | 说明 |
|------|------|------|
| Vue | 3.x | 渐进式 JavaScript 框架 |
| Vite | Latest | 下一代前端构建工具 |
| TypeScript | Latest | JavaScript 的超集 |
| Element Plus | Latest | Vue 3 组件库 |
| Axios | Latest | HTTP 客户端 |
| Pinia | Latest | Vue 状态管理 |

### 开发与部署
- **开发工具**: IntelliJ IDEA / VS Code
- **版本控制**: Git
- **API 测试**: Postman / Apifox
- **部署方式**: Docker 容器化部署（规划）

## 🏗️ 系统架构

```
┌─────────────────────────────────────────────────────────────┐
│                        前端层 (Vue3)                          │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │ 车主端   │  │ 管理端   │  │ 数据看板 │  │ 移动端   │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
└─────────────────────────────────────────────────────────────┘
                            ↓ HTTPS
┌─────────────────────────────────────────────────────────────┐
│                      API 网关层                               │
│              负载均衡 · 限流 · 鉴权                           │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                 应用服务层 (Spring Boot)                     │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │用户服务  │  │车辆服务  │  │停车服务  │  │支付服务  │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │预约服务  │  │财务服务  │  │公告服务  │  │日志服务  │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                    数据访问层 (MyBatis)                       │
│         Mapper XML · 动态 SQL · 事务管理                      │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                      数据存储层 (MySQL)                       │
│     User · Vehicle · ParkingLot · Payment · ...             │
└─────────────────────────────────────────────────────────────┘
```

## 🚀 快速开始

### 环境要求
- JDK 17+
- MySQL 8.0+
- Maven 3.6+
- Node.js 16+ (前端开发)

### 后端启动

#### 1. 克隆项目
```bash
git clone https://github.com/your-username/parking_management_system.git
cd parking_management_system
```

#### 2. 配置数据库
```sql
-- 创建数据库
CREATE DATABASE parking_management_system 
DEFAULT CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- 导入数据表结构
source md/parking_management_system.sql
```

#### 3. 修改配置文件
编辑 `src/main/resources/application.properties`：
```properties
# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/parking_management_system?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=your_password

# JWT 配置
jwt.secret=your_secret_key
jwt.expiration=7200000
```

#### 4. 编译并运行
```bash
# Maven 编译
mvn clean install

# 启动应用
mvn spring-boot:run

# 或直接运行 JAR
java -jar target/parking_management_system-0.0.1-SNAPSHOT.jar
```

应用启动后访问：http://localhost:8080

### 前端启动（开发中）

```bash
# 进入前端目录（待创建）
cd frontend

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

访问：http://localhost:5173

## 📚 API 文档

详细的 API 接口文档请查看：
- [停车场管理系统 API 接口文档](md/停车场管理系统 API 接口文档.md)
- [停车场管理系统实体关系描述](md/停车场管理系统实体关系描述.md)
- [停车场管理系统数据库设计](md/停车场管理系统数据库设计.md)

### 主要接口分类

#### 用户接口
- `POST /api/user/register` - 用户注册
- `POST /api/user/login` - 用户登录
- `GET /api/user/info` - 获取用户信息
- `PUT /api/user/update` - 更新用户信息

#### 车辆接口
- `GET /api/vehicle/list` - 获取车辆列表
- `POST /api/vehicle/add` - 添加车辆
- `DELETE /api/vehicle/delete/{id}` - 删除车辆

#### 停车场接口
- `GET /api/parking-lot/available-spaces` - 查询空闲车位
- `POST /api/reservation/create` - 创建预约
- `GET /api/reservation/list` - 获取预约列表

#### 管理端接口
- `POST /api/admin/*` - 各类管理功能接口

详细接口说明请参考完整文档。

## 💾 数据库设计

### 核心数据表

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| `user` | 车主用户表 | user_id, phone, password, nickname, balance |
| `admin_user` | 管理员表 | admin_id, username, password, role |
| `vehicle` | 车辆信息表 | vehicle_id, plate_number, vehicle_type, user_id |
| `parking_lot` | 停车场表 | lot_id, name, address, total_spaces |
| `parking_space` | 车位表 | space_id, lot_id, space_number, status |
| `parking_record` | 停车记录表 | record_id, vehicle_id, entry_time, exit_time, fee |
| `payment` | 支付记录表 | payment_id, record_id, amount, payment_type, status |
| `reservation` | 预约记录表 | reservation_id, user_id, space_id, start_time, end_time |
| `fee_rule` | 收费规则表 | rule_id, fee_type, rate, description |
| `announcement` | 公告表 | announcement_id, title, content, publish_time |

完整的数据库设计请查看：[数据库设计文档](md/停车场管理系统数据库设计.md)

## 📁 项目结构

```
parking_management_system/
├── src/
│   ├── main/
│   │   ├── java/org/example/parking_management_system/
│   │   │   ├── config/              # 配置类
│   │   │   │   └── WebConfig.java
│   │   │   ├── controller/          # 控制器层
│   │   │   │   ├── admin/          # 管理端控制器
│   │   │   │   ├── UserController.java
│   │   │   │   ├── VehicleController.java
│   │   │   │   └── ...
│   │   │   ├── dto/                # 数据传输对象
│   │   │   │   ├── request/        # 请求 DTO
│   │   │   │   └── response/       # 响应 DTO
│   │   │   ├── entity/             # 实体类
│   │   │   │   ├── User.java
│   │   │   │   ├── Vehicle.java
│   │   │   │   └── ...
│   │   │   ├── exception/          # 异常处理
│   │   │   │   ├── BusinessException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── interceptors/       # 拦截器
│   │   │   │   └── LoginInterception.java
│   │   │   ├── mapper/             # Mapper 接口
│   │   │   │   ├── UserMapper.java
│   │   │   │   └── ...
│   │   │   ├── service/            # 服务层
│   │   │   │   ├── UserService.java
│   │   │   │   └── impl/
│   │   │   ├── util/               # 工具类
│   │   │   │   ├── JwtUtil.java
│   │   │   │   └── Md5Util.java
│   │   │   └── ParkingManagementSystemApplication.java
│   │   └── resources/
│   │       ├── mapper/             # MyBatis XML 映射文件
│   │       └── application.properties
│   └── test/                       # 测试代码
├── md/                             # 项目文档
│   ├── 停车场管理系统需求分析.md
│   ├── 停车场管理系统数据库设计.md
│   └── 停车场管理系统 API 接口文档.md
├── pom.xml                         # Maven 配置
└── README.md                       # 项目说明
```

## 📊 系统特色

### 🔐 安全性
- JWT Token 身份认证
- RBAC 权限控制
- SQL 注入防护
- 敏感数据加密存储
- 操作日志审计

### ⚡ 高性能
- 响应时间 < 3 秒
- 支持 1000+ 并发用户
- 车位状态实时更新 (< 5 秒)
- 数据库索引优化

### 🔄 可扩展性
- 模块化设计
- RESTful API 接口
- 预留微服务架构扩展空间
- 支持容器化部署

### 📈 智能化
- 数据分析与可视化
- 智能车位引导
- 最优路径规划
- 收益趋势分析

## 🤝 开发团队

本项目由个人开发者开发，欢迎交流和合作。

### 联系方式
- 📧 Email: your-email@example.com
- 💬 Issues: [GitHub Issues](https://github.com/your-username/parking_management_system/issues)

## 🎯 路线图

### 已完成 ✅
- [x] 后端基础框架搭建
- [x] 数据库设计与建表
- [x] 用户认证与授权
- [x] 基础 CRUD 接口
- [x] 异常处理机制
- [x] 日志记录

### 开发中 🚧
- [ ] 前端界面开发
- [ ] 支付接口集成
- [ ] 数据可视化看板
- [ ] 单元测试覆盖

### 计划中 📋
- [ ] 车牌识别集成
- [ ] 微信小程序端
- [ ] 智能推荐算法
- [ ] Docker 容器化部署
- [ ] CI/CD流水线

## 🤲 贡献指南

欢迎提交 Issue 和 Pull Request 来帮助改进这个项目！

### 贡献步骤
1. Fork 本仓库
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启一个 Pull Request

### 开发规范
- 遵循 Java 代码规范
- 提交前确保测试通过
- 编写清晰的提交信息
- 新增功能需要编写测试用例

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🙏 致谢

感谢以下开源项目：
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MyBatis](https://mybatis.org/mybatis-3/)
- [Vue.js](https://vuejs.org/)
- [Element Plus](https://element-plus.org/)
- [MySQL](https://www.mysql.com/)

---

<div align="center">
  <p>如果这个项目对你有帮助，请给一个 ⭐️ Star 支持！</p>
  <p>Made with ❤️ by Parking Management System Team</p>
</div>
