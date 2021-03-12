package com.baiye.redscarf.pay.wechat;

import com.baiye.redscarf.pay.constant.WechatPayConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author 白也
 * @date 2021/1/20 11:32 上午
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WechatPayBaseBizResponse extends AbstractWechatPayBaseResponse {

    /** 业务结果. **/
    @XmlElement(name = "result_code")
    private String resultCode;

    /** 错误代码. **/
    @XmlElement(name = "err_code")
    private String errCode;

    /** 错误代码描述. **/
    @XmlElement(name = "err_code_des")
    private String errCodeDes;

    /**
     * 业务是否成功.
     */
    public boolean isBizSuccess() {
        return super.isBaseSuccess() && StringUtils.equals(this.getReturnCode(), WechatPayConstants.SUCCESS);
    }
}
