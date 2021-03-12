package com.baiye.redscarf.pay.util;

import com.baiye.redscarf.common.util.OXMUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.util.Assert;

/**
 * XML Web服务处理器实现.
 *
 * @author 白也
 * @date 2021/1/19 4:25 下午
 */
public class XmlWebServiceHandler<REQUEST_DATA, RESPONSE_DATA> extends AbstractWebServiceHandler<REQUEST_DATA, RESPONSE_DATA> {

    private Class<RESPONSE_DATA> classOfResponseData;

    public XmlWebServiceHandler(Class<RESPONSE_DATA> classOfResponseData) {
        super();
        Assert.notNull(classOfResponseData, "The classOfResponseData must not be null");
        this.classOfResponseData = classOfResponseData;
    }

    @Override
    protected void setRequestData(Request request, REQUEST_DATA requestData) {
        request.bodyString(OXMUtils.marshal(requestData, getCharset()), ContentType.create("text/xml", getCharset()));
    }

    @Override
    protected RESPONSE_DATA parseResponseData(String responseData) {
        return OXMUtils.unmarshal(responseData, classOfResponseData);
    }
}
