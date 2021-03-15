package com.baiye.redscarf.web.common.form;

import com.baiye.redscarf.common.param.PageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author baiye
 * @date 2021/3/15 2:35 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SiegeListForm extends PageForm {

    private String searchKey;
}
