/**
 *
 */
package com.cskaoyan.wxmall.controller;

import com.aliyun.oss.OSSClient;
import com.cskaoyan.mall.bean.generator.Storage;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.component.AliyunComponent;
import com.cskaoyan.mall.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;



@RestController
@RequestMapping("/wx/storage")
public class StorageWxController {

    @Autowired
    StorageService storageService;

    @Autowired
    AliyunComponent aliyunComponent;

    @RequestMapping("upload")
    public BaseReqVo storage(MultipartFile file) throws IOException {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Date date = new Date();
        //url并上传文件
        Storage storage = new Storage();
        String uploadPath = "/upload";
        String realPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static";
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        String filename = s + file.getOriginalFilename();

        //阿里云存储
        OSSClient ossClient = aliyunComponent.getOssClient();
        ossClient.putObject(aliyunComponent.getOss().getBucket(),filename,file.getInputStream());
        String url = "http://" + aliyunComponent.getOss().getBucket() + "." + aliyunComponent.getOss().getEndPoint() +
                "/" + filename;
        storage.setKey(s);
        storage.setName(filename);
        storage.setType(file.getContentType());
        storage.setSize(((int) file.getSize()));
        storage.setUrl(url);
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setDeleted(false);

//        //服务器本地存储
//        String hexstr = Integer.toHexString(filename.hashCode());
//        char[] chars = hexstr.toCharArray();
//        for (char aChar : chars) {
//            uploadPath += "/" + aChar;
//        }
//        uploadPath += "/" + filename;
//        realPath += uploadPath;
//        File file1 = new File(realPath);
//        try {
//            if(!file1.getParentFile().exists()){
//                file1.getParentFile().mkdirs();
//            }
//            file.transferTo(file1);
//            baseReqVo.setErrno(0);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            baseReqVo.setErrno(500);
//            baseReqVo.setErrmsg("failed");
//            return baseReqVo;
//        }
//        String url = "http://" + request.getServerName() //服务器地址
//                + ":"
//                + request.getServerPort()           //端口号
//                + request.getContextPath()      //项目名称
//                + uploadPath;
//        storage.setUrl(url);
//        storage.setKey(UUID.randomUUID().toString());
//        storage.setName(filename);
//        storage.setSize((int)file.getSize());
//        storage.setAddTime(date);
//        storage.setUpdateTime(date);
//        storage.setType(file.getContentType());
//        storage.setDeleted(false);

        int insert = storageService.insertStorage(storage);
        if(insert == 1) {
            baseReqVo.setData(storage);
            baseReqVo.setErrmsg("success");
        }else {
            baseReqVo.setErrmsg("failed");
            baseReqVo.setErrno(500);
        }
        return baseReqVo;
    }
}
