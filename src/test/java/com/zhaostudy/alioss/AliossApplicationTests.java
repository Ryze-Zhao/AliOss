package com.zhaostudy.alioss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AliossApplicationTests {
    @Autowired
    private OSS oss;
    @Value("${aliyun.oss.bucket}")
    private String bucket;

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
    /**
     * 列举存储空间
     */
    @Test
    public void listBuckets() {
        // 列举存储空间。
        List<Bucket> buckets = oss.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }
    }

    /**
     * 简单上传文件
     */
    @Test
    public void putObject() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("D:/Test/测试上传文件.txt");
        oss.putObject(bucket, "测试上传文件.txt", inputStream);
    }
    /**
     * 简单列举文件
     */
    @Test
    public void listObjects() {
        ObjectListing objectListing = oss.listObjects(bucket);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            System.out.println("\t" + s.getKey());
        }
    }
    /**
     * 删除单个文件（有批量删除的，参考阿里云API）
     */
    @Test
    public void deleteObject() {
        // 删除文件。
        oss.deleteObject(bucket, "测试上传文件.txt");
    }
    /**
     * 下载指定文件
     */
    @Test
    public void getObject() {
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        oss.getObject(new GetObjectRequest(bucket, "测试上传文件.txt"), new File("D:/Test/下载回来的测试上传文件.txt"));
    }
}
