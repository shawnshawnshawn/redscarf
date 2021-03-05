package com.baiye.redscarf.common.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author baiye
 * @date 2021/3/4 5:42 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PageForm extends BaseForm{

    private Integer pageNo = 1;

    private Integer pageSize;

    public Integer startPage() {
        return (this.pageNo - 1) * pageSize;
    }
}
