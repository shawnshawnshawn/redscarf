package com.baiye.redscarf.common.elastic;

import lombok.Data;

/**
 * @author baiye
 * @since 2021/6/4 5:49 下午
 **/
@Data
public class LogInfoEntity {

    private String logMsg;

    private String logId;

    private String projectName;

    private String logLevel;

    private String printTime;

}
