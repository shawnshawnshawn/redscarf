package com.baiye.redscarf.common.elastic;

import lombok.Data;

import java.util.List;

/**
 * @author baiye
 * @since 2021/6/5 11:06 上午
 **/
@Data
public class SaveEntityForm {
    private List<LogInfoEntity> logEntities;
}
