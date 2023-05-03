# 南大软件测试中心在线系统 后端项目

> 项目开发过程中的 README，日后将整合至开发文档。
>
> 张宇轩 Kekwy 创建
>
> 2023年4月3日 21:08:07

* [**名词解释**](#名词解释)
* [**开发规范**](#开发规范)
  * [命名规范](#命名规范)
  * [软件包结构规范](#软件包结构规范)
  * [代码结构规范](#代码结构规范)
* [**接口文档**](#接口文档)
  * [Controller](#Controller)
  * [Service](#Service)
  * [Database](#Database)
* [**数据库表设计**](#数据库表设计)
## 参考文档

OpenAPI 规范 (中文版)：https://openapi.apifox.cn

## 名词解释

Dto：Data Transfer Object 数据传输对象；

Pojo：简单的Java对象或者无规则简单java对象，没有业务逻辑的一个中间对象；

// 待续




## 开发规范

> 所有的类与 public 方法均需要添加对应的文档注释。

### 命名规范

1. 软件包命名均以 `com.stcos.模块名` 为前缀（"stcos" 为"software testing center online system"的简写；
2. Java 类以“大坨峰方式命名”，变量名、方法名等均以“小驼峰”方式命名，包名应全部小写；
3. 业务层接口以 `业务名+Service` 格式命名，如用户认证业务接口 `UserAuthenticationService`，其实现类以接口类加上后缀 `Imp` 格式命名。

### 软件包结构规范



### 代码结构规范

在实现类的接口上添加 @Component 注解，在定义接口时编写 default 方法，返回“未实现”；



工具类定义在对应模块的 util 包下，并加上 @UtilityClass 注解。



## 接口文档

> 后端架构中各模块的内部设计以及外部接口。

### Controller



### Service



### Database



## 数据库表设计

账户持久化主要包含：基本信息、角色、权限。

1. 基本信息部分共三张表：

   - `com.stcos.server.pojo.po.Customer` 对应 customer 表；

   - `com.stcos.server.pojo.po.Admin` 对应 admin 表；

   - `com.stcos.server.pojo.po.Operator` 对应 operator 表。

   具体需要包含的字段见类中成员数据的定义。

2. 角色，一张角色表： 

    由于客户（Customer）与平台管理员（Admin）的角色唯一，此处仅需要维护工作人员（Operator）与角色的一对多映射关系。

    表中包含两个字段：工作人员 id 以及角色名称，两者均为字符串。

3. 权限，一张权限表：

    维护工作人员（Operator）与权限的一对多映射关系。同上。



