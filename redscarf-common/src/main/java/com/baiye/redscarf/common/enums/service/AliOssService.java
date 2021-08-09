package com.baiye.redscarf.common.enums.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.model.vo.UploadFileVo;
import com.baiye.redscarf.common.properties.AliyunProperties;
import com.baiye.redscarf.common.result.Result;
import com.baiye.redscarf.common.util.DateUtils;
import com.baiye.redscarf.common.util.RandomKeyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Date;

/**
 * @author baiye
 * @since 2021/8/9 12:15 下午
 **/
@Service
public class AliOssService {

    private final static Logger log = LoggerFactory.getLogger(AliOssService.class);

    @Resource
    private OSS ossClient;

    @Resource
    private AliyunProperties aliyunProperties;

    public UploadFileVo uploadFile(MultipartFile file, String fileType) {
        UploadFileVo vo = new UploadFileVo();
        try {
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isNotBlank(originalFilename)) {
                originalFilename = originalFilename.split("\\.")[0] + "-" + RandomKeyUtils.createRandomKey();
            } else {
                originalFilename = RandomKeyUtils.createRandomKey();
            }
            String path = DateUtils.parseStringByFormat(new Date(), "yyyy-MM-dd");
            String name = path + "/" + originalFilename + "." + fileType;
            String bucketType = getBucketType(fileType);
            PutObjectRequest request = new PutObjectRequest(aliyunProperties.getAliyunOss().getBucketName(), bucketType + "/" + name, file.getInputStream());
            ossClient.putObject(request);
            String url = aliyunProperties.getAliyunOss().getCdnFileUrl() + "/" + bucketType + "/" + name;
            vo.setFileUrl(url);
        } catch (Exception e) {
//            Log.ERROR.print(log, "[阿里云上传图片异常] e -> ", e);
            throw Result.toBizException(ResultCodeEnum.ALIYUN_UPLOAD_IMAGE_ERROR);
        }
        return vo;
    }

    private String getBucketType(String fileType) {
        String bucket = aliyunProperties.getAliyunOss().getInfoBucket();
        if (fileType.equalsIgnoreCase("jpg")
                || fileType.equalsIgnoreCase("png")
                || fileType.equalsIgnoreCase("jpeg")
                || fileType.equalsIgnoreCase("gif")) {
            bucket = aliyunProperties.getAliyunOss().getImageBucket();
        }
        if (fileType.equalsIgnoreCase("mp4")) {
            bucket = aliyunProperties.getAliyunOss().getVideoBucket();
        }
        if (fileType.equalsIgnoreCase("qr")) {
            bucket = aliyunProperties.getAliyunOss().getQrBucket();
        }
        return bucket;
    }
}
