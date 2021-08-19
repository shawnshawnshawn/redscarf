package com.baiye.redscarf.common.event.param;

import lombok.Data;

import java.util.List;

/**
 * @author baiye
 * @date 2021/4/13 11:23 上午
 **/
@Data
public class UpdateTopicParam {

    private String chatid;

    private String name;

    private String owner;

    private List<String> delUserList;

    private List<String> addUserList;
}
