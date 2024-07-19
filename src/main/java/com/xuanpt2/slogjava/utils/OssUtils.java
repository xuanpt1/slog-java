package com.xuanpt2.slogjava.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.GetObjectRequest;
import com.xuanpt2.slogjava.dto.ConstantPropertiesDto;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
public class OssUtils {

    @Resource
    private ConstantPropertiesDto constantPropertiesDTO;


    //文件上传
    public String uploadFileAvatar(MultipartFile file) {
        //获取oss上传配置文件中的参数
        String bucketName = constantPropertiesDTO.getBucketname();
        String endpoint = constantPropertiesDTO.getEndpoint();
        String keyId = constantPropertiesDTO.getKeyid();
        String keySecret = constantPropertiesDTO.getKeysecret();

        OSS ossClient;
        InputStream inputStream;
        try {
            // 创建OSSClient实例。
            ossClient  = new OSSClientBuilder().build(endpoint, keyId, keySecret);
            // 上传文件流
            inputStream = file.getInputStream();

            //为了使得文件可以重复上传，每次上传的时候需要将文件名进行修改
            String fileName = file.getOriginalFilename();
            log.info("图片上传的名字为：{}",fileName);
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String newFileName = uuid + fileName;

            //获取当前日期,然后以日期和新的文件名组成全路径，使得oss中的文件按照日期进行分类存储
            //String date = new DateTimeLiteralExpression.DateTime().toString("yyyy/MM/dd");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String date = LocalDate.now().format(formatter);
            String fullFileName = date + "/" + newFileName;
            log.info("图片保存在oss的全路径为：{}",fullFileName);

            //第一个参数Bucket名称 第二个参数 上传到oss文件路径和文件名称
            ossClient.putObject(bucketName, fullFileName, inputStream);


            // 关闭OSSClient。
            ossClient.shutdown();
            //return "https://"+bucketName+"."+ endpoint+"/"+fullFileName;
            System.out.println("https://"+bucketName+"."+ endpoint+"/"+fullFileName);
            return fullFileName;
        } catch (Exception e) {
            log.error("文件上传失败",e);
            throw new RuntimeException("文件上传oss失败");
        }
    }

    //不使用本地存储文件
//    //文件下载到指定目录中
//    public void downLoadFileAvatar(String yuan, File muDi) {
//        //获取oss上传配置文件中的参数
//        String bucketName = constantPropertiesDTO.getBucketname();
//        String endpoint = constantPropertiesDTO.getEndpoint();
//        String keyId = constantPropertiesDTO.getKeyid();
//        String keySecret = constantPropertiesDTO.getKeysecret();
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, keyId, keySecret);
//
//        // 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
//        // 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
//        ossClient.getObject(new GetObjectRequest(bucketName, yuan),muDi);
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }


}
