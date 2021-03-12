package com.baiye.redscarf.pay.wechat;

import com.baiye.redscarf.pay.constant.WechatPayConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author 白也
 * @date 2021/1/20 11:34 上午
 */
@Data
@XmlAccessorType(value = XmlAccessType.FIELD)
public abstract class AbstractWechatPayBaseResponse {

    /** 返回状态码. **/
    @XmlElement(name = "return_code")
    private String returnCode;

    /** 返回信息. **/
    @XmlElement(name = "return_msg")
    private String returnMsg;

    /**
     * 基础是否成功.
     */
    public boolean isBaseSuccess() {
        return StringUtils.equals(this.getReturnCode(), WechatPayConstants.SUCCESS);
    }
}
