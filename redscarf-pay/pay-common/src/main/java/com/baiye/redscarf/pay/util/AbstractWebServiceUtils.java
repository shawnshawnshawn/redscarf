package com.baiye.redscarf.pay.util;

import org.apache.http.Consts;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import javax.xml.ws.WebServiceException;

/**
 * Web服务工具类.
 *
 * @author 白也
 * @date 2021/1/19 5:25 下午
 */
public abstract class AbstractWebServiceUtils {

    /**
     * post.
     *
     * @param url
     * @param requestData
     * @param handler
     * @param <REQUEST_DATA>
     * @param <RESPONSE_DATA>
     * @return
     */
    public static <REQUEST_DATA, RESPONSE_DATA> RESPONSE_DATA post(String url, REQUEST_DATA requestData,
                                                                   WebServiceHandler<REQUEST_DATA, RESPONSE_DATA> handler) {
        Request request = Request.Post(url);
        handler.handleRequest(request, requestData);

        try {
            Response response = handler.getExecutor(url).execute(request);
            return handler.handleResponse(response);
        } catch (Exception e) {
            throw new WebServiceException(e);
        }
    }

    /**
     * get.
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        Request request = Request.Get(url);

        try {
            Response response = request.execute();
            Content content = response.returnContent();
            return content.asString(Consts.UTF_8);
        } catch (Exception e) {
            throw new WebServiceException(e);
        }
    }
}
