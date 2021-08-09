package com.baiye.redscarf.common.properties;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author baiye
 * @since 2021/8/9 1:49 下午
 **/
@Data
@Component
@NacosConfigurationProperties(dataId = "${nacos.dataId}", prefix = "nacos.dynamic.config", type = ConfigType.YAML, autoRefreshed = true)
public class NacosDynamicProperties {

    private int apiTimeout;

    private String notifyChatId;

    private String errorChatId;

    private String appSysName;

    private String env;

    private Boolean isOpen;

    private Boolean print;

    private Boolean notify;

    private Long slowMills;

    private String userAgreement;

    private String privacyAgreement;

    private boolean concurrencyLimitOn;

    private boolean esLogPrintOn;

    private String defaultReceivePhone;

    private String appShareTitle;

    private String appShareDesc;

    private String appShareImage;

    private String appShareTarget;

    public String toString() {
        return "{" +
                "\n  \"apiTimeout\":" + apiTimeout + "," +
                "\n  \"appSysName\":" + appSysName + "," +
                "\n  \"concurrencyLimitOn\":" + concurrencyLimitOn + "," +
                "\n  \"env\":" + env + "," +
                "\n  \"errorChatId\":" + errorChatId + "," +
                "\n  \"isOpen\":" + isOpen + "," +
                "\n  \"notify\":" + notify + "," +
                "\n  \"notifyChatId\":" + notifyChatId + "," +
                "\n  \"print\":" + print + "," +
                "\n  \"privacyAgreement\":," + privacyAgreement + "," +
                "\n  \"slowMills\":" + slowMills + "," +
                "\n  \"userAgreement\":" + userAgreement +
                "\n  \"esLogPrintOn\":" + esLogPrintOn +
                "\n  \"defaultReceivePhone\":" + defaultReceivePhone +
                "\n  \"appShareTitle\":" + appShareTitle +
                "\n  \"appShareDesc\":" + appShareDesc +
                "\n  \"appShareImage\":" + appShareImage +
                "\n  \"appShareTarget\":" + appShareTarget +
                "\n}";
    }
}
