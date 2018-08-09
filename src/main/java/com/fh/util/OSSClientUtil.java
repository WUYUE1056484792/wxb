package com.fh.util;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class OSSClientUtil {
    public static final Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);
    // endpoint
    //oss-cn-beijing.aliyuncs.com
    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    // accessKey
    private String accessKeyId = "LTAIFffDDz49acVy";
    private String accessKeySecret = "5ZBvC8pS3xUIww8PnIMb8TPyD0UtVu";
    // 空间
    private String bucketName = "wanvision-sit-admin";
    // 文件存储目录
   // private String filedir = "img/wuyue/";
    private OSSClient ossClient;

    public OSSClientUtil() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        System.out.println("OSSClientUtil");
    }
    /**
     * 销毁
     */
    public void destory() {
        System.out.println("destory");
        try {
            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param url
     */
    public  void delOssFile(String url){
        // Object是否存在。
        boolean found = ossClient.doesObjectExist(bucketName, url);
        if(found){
           ossClient.deleteObject(bucketName, url);
         }else {

        }
    }
    /**
     * 上传图片
     *
     * @param url
     * @throws Exception
     */
    public void uploadImg2Oss(String url,String filedir) throws Exception  {
        File fileOnServer = new File(url);
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            this.uploadFile2OSS(fin, split[split.length - 1],filedir);
        } catch (FileNotFoundException e) {
            throw new Exception ("图片上传失败");
        }
    }
    /**
     * 本地路径上传图片并返回路径
     * @author shining
     * @param url ：本地路径
     * @param filedir： 文件夹
     * @throws Exception
     */
    public String uploadImgToOss(String url,String filedir) throws Exception  {
        File fileOnServer = new File(url);
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            this.uploadFile2OSS(fin, split[split.length - 1],filedir);
            String retrunUrl = this.getUrl(filedir+"/"+ split[split.length - 1]);
            return retrunUrl;
        } catch (FileNotFoundException e) {
            throw new Exception ("图片上传失败");
        }
    }
    /**
     *
     * @param file
     * @param filedir 文件目录
     * @return
     * @throws Exception
     */
    public String uploadImg2Oss(MultipartFile file,String filedir) throws Exception {
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new Exception("上传图片大小不能超过10M！");
        }
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename:"+originalFilename);
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name,filedir);
            return name;
        } catch (Exception e) {

            throw new Exception("图片上传失败");
        }
    }
    /**
     * 获得图片路径
     *
     * @param fileUrl:文件名
     * @param filedir:文件目录
     * @return
     */
    public String getImgUrl(String fileUrl,String filedir) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            String url = this.getUrl(filedir+"/"+ split[split.length - 1]);
            if(!"".equals(url)){
                String[] urls =   url.split("[?]");
                System.out.println(urls[1]);
                return urls[0];
            }
        }
        return null;
    }
    /**
     * 上传到OSS服务器 如果同名文件会覆盖服务器上的
     *
     * @param instream
     *            文件流
     * @param fileName
     *            文件名称 包括后缀名
     * @param filedir 文件目录
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream instream, String fileName,String filedir) {
        String ret = "";
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, filedir+"/"+ fileName, instream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param filenameExtension
     *            文件后缀
     * @return String
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
      /*  OSSClientUtil ossClientUtil=new OSSClientUtil();
        String url=ossClientUtil.uploadImgToOss("E:\\QRCodeImage/wanxiang.jpg","ShopQRCode");
        //System.out.println(ossClientUtil.getImgUrl("wanxiang.jpg","ShopQRCode"));
        System.out.println(url);
        ossClientUtil.destory();
*/
                 String  good_count="2.0";
                 float  a =Float.parseFloat(good_count);
                 int    c =(int)a;
                    System.out.println(c);
                // System.out.println(good_count.indexOf("."));
    }
}
