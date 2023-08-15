package com.oracle.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 王飞龙
 * @Date 2023/8/11 14:34
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "project.redis")
@Data
public class RedisConfig {

    private String booksKeyPrefix;
    private String userKeyPrefix;

}
