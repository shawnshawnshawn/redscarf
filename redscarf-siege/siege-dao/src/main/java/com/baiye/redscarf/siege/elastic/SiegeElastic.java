package com.baiye.redscarf.siege.elastic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @author baiye
 * @date 2021/3/7 12:55 上午
 **/
@Data
@Document(indexName = "siege")
public class SiegeElastic {

    @Id
    private Long _id;

    private Long user_id;

    private String siege_images;

    private String create_time;

    private String siege_info;

    private String siege_title;

    private Integer siege_status;

    private Integer siege_type;

    private Integer collect_num;

    private Integer down_num;

    private Integer forward_num;

    private Integer up_num;
}
