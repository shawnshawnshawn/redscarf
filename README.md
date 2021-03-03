# REDSCARF项目介绍

### 简介
> 围绕springcloud相关技术设计的项目，目的是把所学技术统一，具体会涉及订单支付，移动论坛/博客，游戏资讯，后台以及日志。

### 技术概况

基础框架spring系列:

| springboot版本 | springcloud版本 | springcloudAlibaba版本 |
| ------------- | -------------- | ---------------- |
| 2.3.2.RELEASE | Hoxton.SR8 | 2.2.5.RELEASE |


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