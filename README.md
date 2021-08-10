# REDSCARF项目介绍

### 简介
> 围绕springcloud以及springboot相关技术设计的项目，目的是把所学技术统一。
### 技术文档
* [canal安装配置文档](/doc/canal-es.md)
* [elasticsearch基本概念文档](/doc/elastic/es.md)
* [elasticsearch数据迁移文档](/doc/elastic/es-reindex.md)
* [springboot启动过程解析](/doc/spring/springboot-start.md)
### 技术概况

基础框架spring系列:

| springboot版本 | springcloud版本 | springcloudAlibaba版本 |
| ------------- | -------------- | ---------------- |
| 2.3.2.RELEASE | Hoxton.SR8 | 2.2.5.RELEASE |

#### Spring Cloud Alibaba
* [X] Nacos 实现注册中心
* [X] Nacos 实现配置中心
* [X] Sentinel  实现服务容错
* [ ] Seata 实现分布式事务

#### Spring Cloud
* [X] Ribbon 实现负载均衡
* [X] OpenFeign 实现远程调用
* [X] Gateway API网关
* [ ] Sleuth 实现调用链监控

#### Spring Boot
* [X] Mysql 数据持久化
* [X] Redis 热点数据缓存
* [X] Mybatis 持久层框架 

#### Others
* [ ] MQ 消息分发
* [X] Elasticsearch 数据搜索
* [X] OSS 阿里云资源文件存储
* [ ] Elastic-job 分布式调度任务
* [X] wechat扫码支付
* [X] Canal 数据同步
* [X] 自定义缓存CacheJ
* [X] 瞬时qps限制
* [X] 自定义日志(实时上传elasticsearch)
* [ ] JWT+RSA非对称加密登录无状态
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
 |--item-common
 |--item-dao
 |--item-rpc
 |--item-service
 |-redscarf-pay
 |--pay-common
 |--pay-dao
 |--pay-rpc
 |--pay-service
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
6. [redscarf-pay](/redscarf-pay)支付服务   
6. ...

#### 说明
* 关于sentinel的规则持久化，目前使用nacos存储，修改规则也通过nacos配置文件修改，未使用sentinel-dashboard做动态修改。
* mysql数据同步到elasticsearch使用的是alibaba canal组件，后面我会把具体文档同步过来。

