package com.baiye.redscarf.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author baiye
 * @date 2021/3/4 6:06 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> {
    private Integer count;

    private List<T> dtos;
}
