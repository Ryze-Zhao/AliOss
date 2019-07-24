package com.zhaostudy.alioss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * OssClinet
 * @author: HeHaoZhao
 * @date: 2019/7/17 16:26
 */
@Configuration
public class OssConfiguration {
  //读取配置
  @Value("${aliyun.oss.endpoint}")
  private String endpoint;
  @Value("${aliyun.oss.access-key-id}")
  private String accessKeyId;
  @Value("${aliyun.oss.access-key-secret}")
  private String accessKeySecret;
  @Bean
  public OSS oss() {
    return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
  }
}
