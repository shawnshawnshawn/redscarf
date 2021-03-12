package com.baiye.redscarf.siege.form;

import com.baiye.redscarf.common.param.PageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author baiye
 * @date 2021/3/4 5:42 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SiegeListForm extends PageForm {

    private String searchKey;
}
