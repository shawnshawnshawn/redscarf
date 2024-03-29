# 单发短信发送以及日志打印上传es

> 前言：最近优化代码，总结了一点个人的开发经验。由于我这人写代码喜欢偷懒，奉承着能少写一行代码，绝不多写一行的宗旨开发。

## 一.一行代码的初衷
>增删改查大量的业务代码，充斥着setter和getter，一个函数中仅仅只有一处地方还好，多了就会让代码看起了很浑厚，不简洁；
对接了大量的第三方接口，如果要详细的设计每一个接口，势必会造成代码的大量冗余，不简洁；
大量不同的service有相同的方法，仅仅是入参和返回结果不同，做着重复的工作，时间久了就不耐烦了。

## 二. 思考如何简化实现
>每次打印日志时，我们都会定义一个log，然后通过这个log能打印各种异常，然后我就在想，它是怎么实现，怎么就能一行代码就把日志打印出来。然后我各种翻源码，没找到个所以然来，干脆放弃。
但是此时我注意到了枚举，一切都要从这个枚举说起。

## 三. 实现
> 建造者模式。建造者模式可以优化系统中大量的setter，使代码变的十分简洁。实现也很简单，如果你想偷懒，可以直接使用Lombok包中@Builder注解即可。
定义第三方接口的请求接口方法，主要重心放在处理返回结果中。
使用枚举处理第三方提供的服务

## 四. 实例
> 这里着重讲解使用枚举一行代码处理第三方提供的服务。

>系统日志保存到message-send服务的es服务器中
正常来说，请求第三方服务，肯定要通过第三方服务提供的地址，访问他们的接口。这里我们不得不定义一个函数专门用来处理这些操作，然后再在需要的地方调用这个函数。这里又要涉及很多东西，比如每次

> 请求都要创建httpClient，区分日志的类型，每种日志类型的打印的参数不一致，参数个数也不一致，导致参数模板可能就需要好几个。打印日志报错影响到主业务流程怎么办等等一些列问题。已开始我尝试用了这种
方式处理，发现代码量有点大，要定义很多东西。所以我使用了枚举，具体方法如下：

* 定义ES服务客户端 ElasticClient，这个客户端的主要作用就是封装请求es服务的接口以及请求参数
> ![img.png](/doc/images/img_5.png)

* 创建EliasticClient配置ElasticClientConfig，用于注入es客户端以及httpClient
> ![img.png](/doc/images/img_6.png)

* 定义访问es服务的接口，ElasticClient实现这个接口，并提供具体的实现
> ![img.png](/doc/images/img_7.png)![img.png](/doc/images/img_8.png)

* 定义日志枚举，并实现打印日志和上传日志到es的方法，由于枚举类中无法直接注入服务，所以采用内部类的方式加载服务
> ![img.png](/doc/images/img_9.png)![img.png](/doc/images/img_10.png)

* 使用如下图，我们只需要像正常的日志使用即可，在打印日志的同时也会把日志上传到es服务器，即使上传失败报错也不会影响主流程业务，这里要注意尽量在业务代码中使用，如果能明确知道配置的加载顺序，保证ESClient在之前加载，也可在初始化配置中使用。
> ![img.png](/doc/images/img_16.png)

#### 2. aliyun短信发送服务

这里直接描述怎么用枚举一行代码发送短信

* 配置一个短信模板的枚举，枚举包括短信模板code和短信参数
> ![img.png](/doc/images/img_15.png)

* 定义一个发送短信的方法即可
> ![img.png](/doc/images/img_13.png)

* 使用
> ![img.png](/doc/images/img_14.png)

## 五. 总结
> 以前使用枚举仅仅是作为业务状态的多种模板提供选择的作用，现在可以通过枚举简化代码处理各种服务，代码入侵基本为0。