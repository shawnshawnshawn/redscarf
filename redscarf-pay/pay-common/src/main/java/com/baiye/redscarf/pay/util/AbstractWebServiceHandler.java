package com.baiye.redscarf.pay.util;

import lombok.Data;
import org.apache.http.Consts;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import javax.xml.ws.WebServiceException;
import java.nio.charset.Charset;

/**
 * 抽象的Web服务处理器实现.
 * @author 白也
 * @date 2021/1/19 4:21 下午
 */
@Data
public abstract class AbstractWebServiceHandler<REQUEST_DATA, RESPONSE_DATA> implements WebServiceHandler<REQUEST_DATA, RESPONSE_DATA> {

    /**
     * 连接超时时间，单位毫秒.
     */
    private int connectTimeout = 3000;

    /**
     * 传输超时时间，单位毫秒.
     */
    private int socketTimeout = 10000;

    /**
     * 字符集.
     */
    private Charset charset = Consts.UTF_8;

    @Override
    public Executor getExecutor(String url) {
        return Executor.newInstance();
    }

    @Override
    public void handleRequest(Request request, REQUEST_DATA requestData) {
        request.connectTimeout(connectTimeout);
        request.socketTimeout(socketTimeout);
        setRequestData(request, requestData);
    }

    @Override
    public RESPONSE_DATA handleResponse(Response response) {
        try {
            String responseData = response.returnContent().asString(charset);
            return parseResponseData(responseData);
        } catch (Exception e) {
            throw new WebServiceException(e);
        }
    }

    /**
     * 设置请求数据.
     *
     * @param request
     * @param requestData
     */
    protected abstract void setRequestData(Request request, REQUEST_DATA requestData);

    /**
     * 解析响应数据.
     *
     * @param responseData
     * @return
     */
    protected abstract RESPONSE_DATA parseResponseData(String responseData);
}
