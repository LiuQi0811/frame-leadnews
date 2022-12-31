package com.frame.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/*
 *@ClassName OSSDemonstration
 *@Description oss 演示
 *@Author LiuQi
 *@Date 2022/12/31 14:09
 *@Version 1.0
 */
public class OSSDemonstration {
    private static final String ENDPOINT = "https://oss-cn-shanghai.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAI5tPbYakUpup9diuQQFHq";
    private static final String ACCESS_KEY_SECRET = "GSrXxaIXBZeKDaSitBwbb3VFN3JLiq";
    private static final String BUCKET_NAME = "frame-leadnews";
    private static final String PATH = "/Users/liuqi/Desktop/图片素材/WechatIMG9334.jpeg";


    public static void main(String[] args) {
        simpleUpload(ENDPOINT,ACCESS_KEY_ID,ACCESS_KEY_SECRET,BUCKET_NAME,PATH,"baby/小雪.jpeg");
    }

    /**
     * @param endpoint        Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。 https://oss-cn-hangzhou.aliyuncs.com
     * @param accessKeyId     阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
     * @param accessKeySecret
     * @param bucketName      填写Bucket名称，例如examplebucket。
     * @param filePath        填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
     *                        如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
     * @param objectName      填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
     */
    public static void simpleUpload(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String filePath, String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = new FileInputStream(filePath);
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 设置该属性可以返回response。如果不设置，则返回的response为空。
            putObjectRequest.setProcess("true");
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 如果上传成功，则返回200。
            System.out.println(result.getResponse().getStatusCode());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
