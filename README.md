# REDSCARF项目介绍

### 简介
> 围绕springcloud相关技术设计的项目，目的是把所学技术统一，具体会涉及订单支付，移动论坛/博客，游戏资讯，后台以及日志。

### 技术概况

基础框架spring系列:

| springboot版本 | springcloud版本 | springcloudAlibaba版本 |
| ------------- | -------------- | ---------------- |
| 2.3.2.RELEASE | Hoxton.SR8 | 2.2.5.RELEASE |

#### Spring Cloud Alibaba
* Nacos 实现注册中心
* Nacos 实现配置中心
* Sentinel  实现服务容错
* Seata 实现分布式事务
#### Spring Cloud
* Ribbon 实现负载均衡
* OpenFeign 实现远程调用
* Gateway API网关
* Sleuth 实现调用链监控
#### Spring Boot
* Mysql 数据持久化
* Redis 热点数据缓存
* Mybatis 持久层框架 
#### Others
* RocketMQ 消息分发
* ELK 大数据搜索
* OSS 阿里云静态文件存储
* Elastic-job 分布式定时任务
* WeChatPay 微信支付
* ...


### 项目结构
```
redscarf
 |-redscrf-common
 |-redscarf-gateway
 |-redscarf-user-service
 |--nacos-user-service
 |--user-common
 |--user-dao
 |--user-rpc
 ...
```
### 必要服务
* nacos提供配置中心和服务发现，需要搭建nacos服务，可参考官方文档[nacos快速开始](https://nacos.io/zh-cn/docs/quick-start.html)
* mysql数据存储服务
* redis缓存服务
* elk搜索服务

### 功能描述
