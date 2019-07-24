package com.zhaostudy.alioss;

import com.aliyun.oss.OSS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AliossApplicationTests {
    @Autowired
    private OSS oss;

    /**
     * 创建存储空间
     */
    @Test
    public void createBucket() {
        // 创建存储空间，这个名字相当于文件夹名字，
        // 我配置文件写的是这个名字，我也写回这个名字
        String bucketName = "resume-test";
        // 新建存储空间默认为标准存储类型，私有权限。
        oss.createBucket(bucketName);
    }


}
