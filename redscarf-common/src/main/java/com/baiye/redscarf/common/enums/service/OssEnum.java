package com.baiye.redscarf.common.enums.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author baiye
 * @since 2021/8/9 12:08 下午
 **/
public enum OssEnum {

    INFO,
    IMAGE,
    VIDEO,
    ;

    private MultipartFile file;

    private String fileType;

    OssEnum() {
    }

    OssEnum(MultipartFile file, String fileType) {
        this.file = file;
        this.fileType = fileType;
    }

    private AliOssService aliOssService;

    public void upload(MultipartFile file, String fileType) {
        aliOssService.uploadFile(file, fileType);
    }

    @Component
    public static class ServiceInjector {
        public ServiceInjector(@Qualifier("aliOssService") AliOssService aliOssService) {
            INFO.aliOssService = aliOssService;
            IMAGE.aliOssService = aliOssService;
            VIDEO.aliOssService = aliOssService;
        }
    }
}
