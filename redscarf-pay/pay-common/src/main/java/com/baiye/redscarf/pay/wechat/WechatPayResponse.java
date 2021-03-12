package com.baiye.redscarf.pay.wechat;

import com.baiye.redscarf.pay.constant.WechatPayConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 白也
 * @date 2021/1/20 11:34 上午
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "xml")
public class WechatPayResponse extends AbstractWechatPayBaseResponse {

    /**
     * 无参构造.
     */
    public WechatPayResponse() {
        super();
    }

    /**
     * 有参构造.
     */
    public WechatPayResponse(boolean isSuccess, String returnMsg) {
        super();
        super.setReturnCode(isSuccess ? WechatPayConstants.SUCCESS : WechatPayConstants.FAIL);
        super.setReturnMsg(returnMsg);
    }
}
