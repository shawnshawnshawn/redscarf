package com.baiye.redscarf.pay.util;

import com.baiye.redscarf.common.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.util.Assert;

/**
 * Json Web服务处理器实现.
 * @author 白也
 * @date 2021/1/19 5:25 下午
 */
public class JsonWebServiceHandler<REQUEST_DATA, RESPONSE_DATA> extends AbstractWebServiceHandler<REQUEST_DATA, RESPONSE_DATA> {

    private Class<RESPONSE_DATA> classOfResponseData;

    private TypeReference<RESPONSE_DATA> typeReferenceOfResponseData;

    public JsonWebServiceHandler(Class<RESPONSE_DATA> classOfResponseData) {
        super();
        Assert.notNull(classOfResponseData, "The class of response data must not be null");
        this.classOfResponseData = classOfResponseData;
    }

    public JsonWebServiceHandler(TypeReference<RESPONSE_DATA> typeReferenceOfResponseData) {
        super();
        Assert.notNull(typeReferenceOfResponseData, "The type reference of response data must not be null");
        this.typeReferenceOfResponseData = typeReferenceOfResponseData;
    }

    @Override
    protected void setRequestData(Request request, REQUEST_DATA requestData) {
        String json = JsonUtils.toJson2(requestData);
        request.bodyString(json, ContentType.create("application/json", getCharset()));
    }

    @Override
    protected RESPONSE_DATA parseResponseData(String responseData) {
        if (classOfResponseData != null) {
            return JsonUtils.toObj2(responseData, classOfResponseData);
        }

        return JsonUtils.toObj2(responseData, typeReferenceOfResponseData);
    }
}
