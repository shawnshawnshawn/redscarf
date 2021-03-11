## springboot启动过程解析

### 背景图

> ![img.png](/doc/images/spring-start.png)  
> 一切要从这张图说起，这张图是当初在一家公司里，大佬给我们讲的关于springboot的启动过程，目的是让我们快速熟悉使用公司自研的框架。
> 过了这么久我也忘了差不多，现在通过这张图结合源码来详细了解一下。

* 第一步 实例化SpringApplication

源码如下 
```
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {    
    this.resourceLoader = resourceLoader;    
    Assert.notNull(primarySources, "PrimarySources must not be null");    
    this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));    
    ① this.webApplicationType = WebApplicationType.deduceFromClasspath();  
    ② setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));   
    ③ setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));   
    ④ this.mainApplicationClass = deduceMainApplicationClass();   
}  
```
参数有两个`resourceLoader`和`primarySources`  
`resourceLoader`：资源加载器，它只加载`classpath:`下的单资源文件，多资源文件加载在它的子接口`ResourcePatternResolver`中实现。  
`primarySources`：要加载的主要资源，既是启动类  
主要代码是上面四个带编号的代码：  
①：获取应用的类型，分别有`REACTIVE`、`NONE`、`SERVLET`;  
`REACTIVE`：应用程序应该作为一个响应式web应用程序运行，并且应该启动一个嵌入式响应式web服务器。  
`SERVLET`：应用程序应该作为基于servlet的web应用程序运行，并且应该启动嵌入式servlet web服务器。  
`NONE`：应用程序不应该作为web应用程序运行，也不应该启动嵌入式web服务器。    
②：使用SpringFactoriesLoader查找并加载 classpath下`META-INF/spring.factories`文件中所有可用的 ApplicationContextInitializer    
③：使用SpringFactoriesLoader查找并加载 classpath下`META-INF/spring.factories`文件中的所有可用的 ApplicationListener    
④：设置main方法的定义类（不得不吐槽一下，从异常中获取方法名称，真有他们的）    
以上就完成SpringApplication实例化
* 第二步调用run方法
源码如下，我直接结合源码解释每一步都干了啥
```
public ConfigurableApplicationContext run(String... args) {

    ①：StopWatch是用来计时执行代码的类
    StopWatch stopWatch = new StopWatch();
    
    ②：计时开始
    stopWatch.start();
    
    ③：定义ApplicationContext
       ConfigurableApplicationContext是ApplicationContext的子类。ApplicationContext本身并没有提供获取set方法，所以需要通过子类获取应用程序上下文。
       除此之外他还继承了Lifecycle和Closeable。Lifecycle顾名思义就是负责对context的生命周期进行管理，提供了start()和stop()以及isRunning()方法。
       Closeable接口是JDK提供的接口，用于关闭组件，释放资源。
       所以ConfigurableApplicationContext接口的作用就是设置上下文ID，设置父应用上下文，添加监听器，刷新容器，关闭，判断是否活跃，加载资源等方法。
    ConfigurableApplicationContext context = null;
    
    ④：异常报告器，初始化构造一个SpringBootExceptionReporter集合,这玩意牵扯的东西很多就不多说了，感兴趣的同学可以独自了解
    Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
    
    ⑤：设置系统属性 `java.awt.headless` 的值，默认值为：true
    configureHeadlessProperty();
    
    ⑥：SpringApplication初始化的时候会加载SpringApplicationRunListener对象。
       创建所有 Spring 运行监听器并发布应用启动事件
       监听者模式，发布一个事件，监听器监听事件并作出反应。
       发布ApplicationStartingEvent事件
    SpringApplicationRunListeners listeners = getRunListeners(args);
    listeners.starting();
    try {
        
        ⑦：初始化默认应用参数类，装载命令行传入springboot应用程序的参数到ApplicationArguments对象中
        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        
        ⑧：根据监听器和应用参数准备spring环境，源码分析在下面
           发布ApplicationEnvironmentPreparedEvent事件
        ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
        
        ⑨：配置spring.beaninfo.ignore
        configureIgnoreBeanInfo(environment);
        
        ⑩：打印banner
        Banner printedBanner = printBanner(environment);
        
        ⑪：创建ApplicationContext
        context = createApplicationContext();
        
        ⑫：准备异常报告器
        exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
                new Class[] { ConfigurableApplicationContext.class }, context);
        
        ⑬：准备应用上下文，发布ApplicationContextInitializedEvent事件
        prepareContext(context, environment, listeners, applicationArguments, printedBanner);
        
        ⑭：刷新应用上下文
        refreshContext(context);
        
        ⑮：应用上下文刷新后置处理
        afterRefresh(context, applicationArguments);
        
        ⑯：停止计时监控类
        stopWatch.stop();
        
        ⑰：输出日志记录执行主类名、时间信息
        if (this.logStartupInfo) {
            new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
        }
        
        ⑱：发布应用上下文启动完成事件，发布ApplicationStartedEvent事件
        listeners.started(context);
        
        ⑲：执行所有 Runner 运行器
        callRunners(context, applicationArguments);
    }
    catch (Throwable ex) {
        ⑳：启动异常处理，发布异常事件
        handleRunFailure(context, ex, exceptionReporters, listeners);
        throw new IllegalStateException(ex);
    }

    try {
        ㉑：发布应用上下文就绪事件，发布ApplicationReadyEvent事件
        listeners.running(context);
    }
    catch (Throwable ex) {
        handleRunFailure(context, ex, exceptionReporters, null);
        throw new IllegalStateException(ex);
    }
    ㉒：返回应用上下文
    return context;
}

⑧源码分析
private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners,
        ApplicationArguments applicationArguments) {
    // 获取或创建环境，根据上面SpringApplication实例化时的应用类型设置
    ConfigurableEnvironment environment = getOrCreateEnvironment();
    
    // 根据properties和profiles配置环境
    configureEnvironment(environment, applicationArguments.getSourceArgs());
    ConfigurationPropertySources.attach(environment);
    
    // 发布环境准备完成事件
    listeners.environmentPrepared(environment);
    
    // 将环境绑定到spring应用程序
    bindToSpringApplication(environment);
    if (!this.isCustomEnvironment) {
        environment = new EnvironmentConverter(getClassLoader()).convertEnvironmentIfNecessary(environment,
                deduceEnvironmentClass());
    }
    ConfigurationPropertySources.attach(environment);
    return environment;
}
```  
* 总结
> 如果你想看到运行时内部的参数变化以及详细过程，可以断点启动。
> 也可以实现spring自带的监听器，监听启动过程发出的事件

### 文章引用
* [SpringBoot 应用程序启动过程探秘](https://juejin.cn/post/6844903669998026759)
* [ConfigurableApplicationContext定义上下文](https://juejin.cn/post/6844903936663502856)
* [SpringBootExceptionReporter异常报告器解析](https://www.jianshu.com/p/5932371a8170)