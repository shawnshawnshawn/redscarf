package com.baiye.redscarf.common.elastic;

import com.baiye.redscarf.common.exception.BizException;
import lombok.Data;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * es客户端
 * <p>
 * 构建请求日志client，封装请求接口
 * 可通过@Bean配置交由spring管理，具体配置可参见{@link com.baiye.redscarf.common.elastic.ElasticClientConfig}
 * </p>
 * <p>
 * Use: 通过{@link com.baiye.redscarf.common.elastic.Log}获取日志等级，然后调用方法{@link com.baiye.redscarf.common.elastic.Log#print(Logger, String)} ()}打印日志。
 * 该方法通过参数Logger打印对应的日志信息并调用{@link com.baiye.redscarf.common.elastic.Log#execute(String)}请求
 * {@link com.baiye.redscarf.common.elastic.ElasticClient#executePostSaveLog(String, String)}接口，保存日志到es。
 * </p>
 *
 * @author baiye
 * @since 2021/6/4 5:48 下午
 **/
@Data
public class ElasticClient implements ElasticRequestApi {

    private static final Logger log = LoggerFactory.getLogger(ElasticClient.class);

    /**
     * 请求uri
     */
    private String uri;

    /**
     * 用户密匙
     */
    private String appKey;

    /**
     * 用户秘钥
     */
    private String secret;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * rest客户端
     */
    private RestTemplate restTemplate;

    /**
     * 日志id
     */
    private String logId;

    /**
     * 日志等级
     */
    private String logLevel;

    /**
     * 打印时间
     */
    private String printTime;

    /**
     * 系统环境
     */
    private String env;

    ElasticClient(Builder builder) {
        this.appKey = builder.appKey;
        this.secret = builder.secret;
        this.uri = builder.uri;
        this.restTemplate = builder.restTemplate;
        this.projectName = builder.project;
        this.logId = builder.logId;
        this.logLevel = builder.logLevel;
        this.logLevel = builder.logLevel;
        this.printTime = builder.printTime;
        this.env = builder.env;
    }

    @Override
    public void executePostSaveLog(String logLevel, String message) {
        List<LogInfoEntity> logInfoEntities = new ArrayList<>();
        LogInfoEntity logInfoEntity = new LogInfoEntity();
        String logId = parseStringByFormat(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(10000, 99999);
        logInfoEntity.setLogId(this.projectName + "-" + logId);
        logInfoEntity.setLogLevel(logLevel);
        logInfoEntity.setLogMsg(message.substring(0, Math.min(32765, message.length())));
        logInfoEntity.setProjectName(this.projectName);
        logInfoEntity.setPrintTime(parseStringByFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        HttpHeaders headers = new HttpHeaders();
        logInfoEntities.add(logInfoEntity);
        SaveEntityForm form = new SaveEntityForm();
        form.setLogEntities(logInfoEntities);
        HttpEntity<SaveEntityForm> httpEntity = new HttpEntity<>(form, headers);
        String uri = this.uri + "es/log/commit/" + this.env;
        ResponseEntity<Object> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Object.class);
        if (exchange.getStatusCodeValue() != 200) {
            log.error("保存日志到es失败 res -> {}", exchange);
        }
    }

    public static void main(String[] args) {
        System.out.println("ssss".getBytes(StandardCharsets.UTF_8).length);
        System.out.println("ssss".length());
    }

    public static String parseStringByFormat(Date date, String format) {
        if (date == null || StringUtils.isBlank(format)) {
            throw new BizException("日期转化参数不能为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    @Override
    public LogInfoEntity executeGetLog(String logId) {
        ResponseEntity<LogInfoEntity> forEntity = restTemplate.getForEntity(this.uri + "/getLog/" + logId, LogInfoEntity.class);
        if (forEntity.getStatusCodeValue() == 200) {
            return forEntity.getBody();
        }
        return null;
    }

    public ElasticClient logLevel(String logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public static final class Builder {

        private String uri;

        private String appKey;

        private String secret;

        private RestTemplate restTemplate;

        private String project;

        private String logId;

        private String logLevel;

        private String printTime;

        private String env;

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder appKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        public Builder secret(String secret) {
            this.secret = secret;
            return this;
        }

        public Builder restTemplate(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
            return this;
        }

        public Builder project(String project) {
            this.project = project;
            return this;
        }

        public Builder logId(String logId) {
            this.logId = logId;
            return this;
        }

        public Builder logLevel(String logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder printTime(String printTime) {
            this.printTime = printTime;
            return this;
        }

        public Builder env(String env) {
            this.env = env;
            return this;
        }

        public Builder() {
            this.appKey = "";
            this.secret = "";
            this.uri = "http://localhost:8090/";
            this.restTemplate = new RestTemplate();
            this.project = "ALL";
            this.logId = this.project + "-" + UUID.randomUUID().toString().replaceAll("-", "");
            this.logLevel = "INFO";
            this.printTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            this.env = "dev";
        }

        public Builder(ElasticClient elasticClient) {
            this.uri = elasticClient.uri;
            this.appKey = elasticClient.appKey;
            this.secret = elasticClient.secret;
            this.restTemplate = elasticClient.restTemplate;
            this.project = elasticClient.projectName;
            this.logId = elasticClient.logId;
            this.logLevel = elasticClient.logLevel;
            this.printTime = elasticClient.printTime;
            this.env = elasticClient.env;
        }

        public ElasticClient build() {
            return new ElasticClient(this);
        }
    }
}
