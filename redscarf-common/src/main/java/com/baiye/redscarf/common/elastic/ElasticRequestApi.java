package com.baiye.redscarf.common.elastic;


/**
 * @author baiye
 * @since 2021/6/4 5:50 下午
 **/
public interface ElasticRequestApi {

    /**
     * 保存日志到es
     *
     * @param logLevel 日志级别
     * @param message  日志
     */
    void executePostSaveLog(String logLevel, String message);

    /**
     * 获取日志信息
     *
     * @param logId 日志id
     * @return 日志信息
     */
    LogInfoEntity executeGetLog(String logId);
}
