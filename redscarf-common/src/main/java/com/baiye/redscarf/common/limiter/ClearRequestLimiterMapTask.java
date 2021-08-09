package com.baiye.redscarf.common.limiter;

import com.baiye.redscarf.common.elastic.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author baiye
 * @since 2021/5/31 4:37 下午
 **/
@Component
public class ClearRequestLimiterMapTask extends RequestLimiterUtils {

    private static final Logger log = LoggerFactory.getLogger(ClearRequestLimiterMapTask.class);

    @Scheduled(cron = "0 * * * * ?")
    public void clearRequestLimiterMap() {
        Log.INFO.print(log, "执行清除[RequestLimiter]本地缓冲！");
        int size = getRequestLimiterMapSize();
        Log.INFO.print(log, "[RequestLimiterMap] -> size: {}", size);
        if (size > 0) {
            getRequestLimiterMap().clear();
            Log.INFO.print(log, "[RequestLimiterMap] -> size: {}", getRequestLimiterMapSize());
        }
    }

}
