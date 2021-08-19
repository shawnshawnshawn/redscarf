package com.baiye.redscarf.common.event.param;

import lombok.Data;

import java.util.List;

/**
 * @author baiye
 * @since  2021/4/13 10:41 上午
 **/
@Data
public class CreateTopicParam {

    private String name;

    private String owner;

    private List<String> userlist;
}
