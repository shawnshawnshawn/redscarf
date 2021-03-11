# REDSCARF项目介绍

### 简介
> 围绕springcloud相关技术设计的项目，目的是把所学技术统一，具体会涉及订单支付，移动论坛/博客，游戏资讯，后台以及日志。
### 相关文档
* [canal安装配置文档](/doc/canal-es.md)
* [elasticsearch基本概念文档](/doc/elastic/es.md)
* [elasticsearch数据迁移文档]()
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
* Elasticsearch 大数据搜索
* OSS 阿里云静态文件存储
* Elastic-job 分布式调度任务
* WeChatPay 微信支付
* Canal 数据同步  
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
 |-redscarf-siege
 |--siege-common
 |--siege-dao
 |--siege-rpc
 |--siege-service
 |-redscarf-cache
 |-redscarf-item
 |-item-common
 |-item-dao
 |-item-rpc
 |-item-service
 ...
```
### 必要服务
* nacos提供配置中心和服务发现，需要搭建nacos服务，可参考官方文档[nacos快速开始](https://nacos.io/zh-cn/docs/quick-start.html)
* mysql数据存储服务
* redis缓存服务
* elasticsearch搜索服务
* canal数据同步服务

### 功能描述
1. [redscarf-common](/redscarf-common)项目基础工具包包含返回信息视图，公共工具包，公共枚举以及业务异常。
2. [redscarf-gateway](/redscarf-gateway)项目网关
3. [redscarf-user-service](/redscarf-user-service)用户相关会员服务
4. [redscarf-cache](/redscarf-cache)项目缓存服务   
5. [redscarf-siege](/redscarf-siege)围城(帖子)业务服务
6. [redscarf-item](/redscarf-item)商品服务   
6. ...

#### 说明
* 关于sentinel的规则持久化，目前使用nacos存储，修改规则也通过nacos配置文件修改，未使用sentinel-dashboard做动态修改。
* mysql数据同步到elasticsearch使用的是alibaba canal组件，后面我会把具体文档同步过来。

