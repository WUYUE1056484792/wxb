package com.fh.controller.works;
import com.fh.controller.base.BaseController;
import com.fh.util.DataMsg;
import com.fh.util.OSSClientUtil;
import com.fh.util.ParamData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 30000)
@RestController
@RequestMapping("api")
public class UpImgController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(UpImgController.class);
    @RequestMapping(value = "/imgUpload")
    public DataMsg headImgUpload(HttpServletRequest request) {
        DataMsg dataMsg = new DataMsg();
        ParamData pd =new ParamData();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("multipartRequest:"+multipartRequest);
        MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();
        List<MultipartFile> files = multiValueMap.get("file");
        System.out.println("multiValueMap:"+multiValueMap);
        String filedir ="";//文件目录
        filedir =  multipartRequest.getParameter("filedir");
        System.out.println("filedir:"+filedir);
        StringBuilder imgUrl = new StringBuilder();
        StringBuilder objectName = new StringBuilder();
        OSSClientUtil ossClient=new OSSClientUtil();
        try {
            if (""!=filedir&&null!=filedir) {
                String  filedirs = filedir.replaceAll("\"", "");
                for (MultipartFile file:files){
                    String name = ossClient.uploadImg2Oss(file,filedirs);
                    String  img =  ossClient.getImgUrl(name,filedirs);
                    imgUrl.append(img).append(",");
                    String imgObject =filedirs+"/"+name;
                    objectName.append(imgObject).append(",");
                }
            }
        } catch (Exception e) {
            dataMsg.setStateCode("Exception");
            dataMsg.setStateMsg("fail");
            e.printStackTrace();
        }finally {
            ossClient.destory();
        }
        String img =imgUrl.toString();
        String imgObject =objectName.toString();
        pd.put("imgUrl", img.substring(0,img.length()-1));
        pd.put("objectName",imgObject.substring(0,imgObject.length()-1));
        dataMsg.setData(pd);
        System.out.println("Pd:"+ pd);
        return dataMsg;
    }

    /**
     *  pd objectName  objectName=img/czy/1528102211378.png
     * @return
     */
    @RequestMapping(value ="delGetOssFile")
    public DataMsg delGetOssFile(){
        log.info("删除图片");
        DataMsg  dataMsg = new DataMsg();
        ParamData pd = this.getParamData();
        OSSClientUtil ossClient=new OSSClientUtil();
        try {
            ossClient.delOssFile(pd.getString("objectName"));
        } catch (Exception e) {
            dataMsg.setStateCode("Exception");
            dataMsg.setStateMsg("fail");
            e.printStackTrace();
        }
        ossClient.destory();
        return dataMsg;
    }
    /**
     *  pd objectName  objectName=img/czy/1528102211378.png
     * @return
     */
    @RequestMapping(value ="delPostOssFile")
    public DataMsg delPostOssFile(@RequestBody ParamData pd){
        log.info("删除图片");
        DataMsg  dataMsg = new DataMsg();
        //ParamData pd = this.getParamData();
        OSSClientUtil ossClient=new OSSClientUtil();
        try {
            ossClient.delOssFile(pd.getString("objectName"));
        } catch (Exception e) {
            dataMsg.setStateCode("Exception");
            dataMsg.setStateMsg("fail");
            e.printStackTrace();
        }
        ossClient.destory();
        return dataMsg;
    }
    @PostMapping(value = "imgUploadPost")
    public DataMsg imgUploadPost(HttpServletRequest request){
        DataMsg  dataMsg = new DataMsg();
        ParamData pd =new ParamData();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("multipartRequest:"+multipartRequest);
        MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();
        List<MultipartFile> files = multiValueMap.get("file");
        System.out.println("multiValueMap:"+multiValueMap);
        String filedir ="";//文件目录
        filedir =  multipartRequest.getParameter("filedir");
        System.out.println("filedir:"+filedir);
        StringBuilder imgUrl = new StringBuilder();
        StringBuilder objectName = new StringBuilder();
        OSSClientUtil ossClient=new OSSClientUtil();
        try {
            if (""!=filedir&&null!=filedir) {
                String  filedirs = filedir.replaceAll("\"", "");
                for (MultipartFile file:files){
                    String name = ossClient.uploadImg2Oss(file,filedirs);
                    String  img =  ossClient.getImgUrl(name,filedirs);
                    imgUrl.append(img).append(",");
                    String imgObject =filedirs+"/"+name;
                    objectName.append(imgObject).append(",");
                }
            }
        } catch (Exception e) {
            dataMsg.setStateCode("Exception");
            dataMsg.setStateMsg("fail");
            e.printStackTrace();
        }finally {
            ossClient.destory();
        }
        String img =imgUrl.toString();
        String imgObject =objectName.toString();
        pd.put("imgUrl", img.substring(0,img.length()-1));
        pd.put("objectName",imgObject.substring(0,imgObject.length()-1));
        dataMsg.setData(pd);
        return dataMsg;
    }
}

