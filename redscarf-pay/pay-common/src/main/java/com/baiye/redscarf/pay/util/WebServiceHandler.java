package com.baiye.redscarf.pay.util;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

/**
 * Web服务处理器.
 * @author 白也
 * @date 2021/1/19 5:20 下午
 */
public interface WebServiceHandler<REQUEST_DATA, RESPONSE_DATA> {

    /**
     * 获取Executor.
     *
     * @param url
     * @return
     */
    Executor getExecutor(String url);

    /**
     * 处理请求，比如：设置请求参数和请求数据/
     *
     * @param request
     * @param requestData
     */
    void handleRequest(Request request, REQUEST_DATA requestData);

    /**
     * 处理响应，比如：将响应内容转换为对象.
     *
     * @param response
     * @return
     */
    RESPONSE_DATA handleResponse(Response response);
}
