package com.xuanpt2.slogjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xuanpt2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class ConstantPropertiesDto {

    private String endpoint;
    private String keyid;
    private String keysecret;
    private String bucketname;
}


