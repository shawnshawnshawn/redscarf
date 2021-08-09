package com.baiye.redscarf.common.elastic;

import com.baiye.redscarf.common.properties.NacosDynamicProperties;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author baiye
 * @since 2021/6/5 11:37 上午
 **/
public enum Log {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    ;

    private ElasticClient elasticClient;

    private NacosDynamicProperties nacosDynamicProperties;

    public void print(Logger log, String msg) {
        if (nacosDynamicProperties.isEsLogPrintOn()) {
            threadPoolExecutor.execute(() -> execute(msg));
        }
        switch (this.name()) {
            case "DEBUG":
                log.debug(msg);
                break;
            case "INFO":
                log.info(msg);
                break;
            case "WARN":
                log.warn(msg);
                break;
            case "ERROR":
                log.error(msg);
                break;
        }
    }

    public void print(Logger log, String v, Object... obj) {
        if (nacosDynamicProperties.isEsLogPrintOn()) {
            String msg = getMsg(v, obj);
            threadPoolExecutor.execute(() -> execute(msg));
        }
        switch (this.name()) {
            case "DEBUG":
                log.debug(v, obj);
                break;
            case "INFO":
                log.info(v, obj);
                break;
            case "WARN":
                log.warn(v, obj);
                break;
            case "ERROR":
                log.error(v, obj);
                break;
        }
    }

    private static String getMsg(String v, Object... objects) {
        String[] split = v.split("\\{}");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i > objects.length - 1) {
                sb.append(split[i]);
                continue;
            }
            sb.append(split[i]).append(objects[i]);
        }
        return sb.toString();
    }

    protected void execute(String msg) {
        elasticClient.logLevel(this.name()).executePostSaveLog(this.name(), msg);
    }

    @Component
    public static class ServiceInjector {
        public ServiceInjector(@Qualifier("elasticClient") ElasticClient elasticClient,
                               @Qualifier("nacosDynamicProperties") NacosDynamicProperties nacosDynamicProperties) {
            DEBUG.elasticClient = elasticClient;
            DEBUG.nacosDynamicProperties = nacosDynamicProperties;
            INFO.elasticClient = elasticClient;
            INFO.nacosDynamicProperties = nacosDynamicProperties;
            WARN.elasticClient = elasticClient;
            WARN.nacosDynamicProperties = nacosDynamicProperties;
            ERROR.elasticClient = elasticClient;
            ERROR.nacosDynamicProperties = nacosDynamicProperties;
        }
    }

    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            availableProcessors,
            20,
            100L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        String s = "[yichang] a -> {}, b -> ";
        String aaaaaa = getMsg(s, "(Object) null", "2", 3);
        System.out.println(aaaaaa);
    }
}
