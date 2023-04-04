# 南大软件测试中心在线系统 后端项目

> 项目开发过程中的 README，日后将整合至开发文档。
>
> 张宇轩 Kekwy 创建
>
> 2023年4月3日 21:08:07

* [**开发规范**](#开发规范)
  * [命名规范](#命名规范)
  * [软件包结构规范](#软件包结构规范)
  * [代码结构规范](#代码结构规范)
* [**接口文档**](#接口文档)
  * [Controller](#Controller)
  * [Service](#Service)
  * [Database](#Database)

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



### 