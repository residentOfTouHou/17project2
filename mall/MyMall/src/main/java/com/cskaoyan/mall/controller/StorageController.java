/**
 *
 */
package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.generator.Storage;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.StorageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("admin/storage")
public class StorageController {

    @Autowired
    StorageService storageService;


    @RequestMapping("create")
    public BaseReqVo create1(MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Date date = new Date();
        //url并上传文件
        String uploadPath = "/upload";
        String realPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static";
        String filename = UUID.randomUUID() + file.getOriginalFilename();
        String hexstr = Integer.toHexString(filename.hashCode());
        char[] chars = hexstr.toCharArray();
        for (char aChar : chars) {
            uploadPath += "/" + aChar;
        }
        uploadPath += "/" + filename;
        realPath += uploadPath;
        File file1 = new File(realPath);
        try {
            if(!file1.getParentFile().exists()){
                file1.getParentFile().mkdirs();
            }
            file.transferTo(file1);
            baseReqVo.setErrno(0);

        } catch (IOException e) {
            e.printStackTrace();
            baseReqVo.setErrno(500);
            baseReqVo.setErrmsg("failed");
            return baseReqVo;
        }
        String url = "http://" + request.getServerName() //服务器地址
                + ":"
                + request.getServerPort()           //端口号
                + request.getContextPath()      //项目名称
                + uploadPath;
        Storage storage = new Storage();
        storage.setUrl(url);
        storage.setKey(UUID.randomUUID().toString());
        storage.setName(filename);
        storage.setSize((int)file.getSize());
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        storage.setType(file.getContentType());
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

    @RequestMapping("list")
    public BaseReqVo list(@Param("page")int page, @Param("limit")int limit,
                          @Param("sort")String sort, @Param("order")String order,
                          String name,String key){
        PageHelper.startPage(page, limit);
        List<Storage> storages = storageService.getStoragesOrderBy(sort,order,name,key);
        PageInfo<Storage> storagePageInfo = new PageInfo<>(storages);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",storagePageInfo.getTotal());
        map.put("items",storagePageInfo.getList());
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("success");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Storage storage,HttpServletRequest request){
        storage.setUpdateTime(new Date());
        String url = storage.getUrl();
        String replace = url.replaceAll("http://" + request.getServerName() //服务器地址
                + ":"
                + request.getServerPort()           //端口号
                + request.getContextPath(), "");
        String realPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static" + replace;
        String[] split = realPath.split("/");
        String oldName = split[split.length - 1];
        File file = new File(realPath);
        boolean isRenamed = file.renameTo(new File(realPath.replaceAll(oldName, storage.getName())));
        int update = 0;
        if(isRenamed){
            update = storageService.updateStorage(storage);
            storage.setUrl(url.replaceAll(oldName,storage.getName()));
        }

        BaseReqVo baseReqVo = new BaseReqVo();
        if(update == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("update success");
            baseReqVo.setData(storage);
        }else {
            baseReqVo.setErrmsg("failed");
            baseReqVo.setErrno(500);
        }
        return baseReqVo;
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Storage storage,HttpServletRequest request){
        int delete = storageService.deleteStorage(storage);
        BaseReqVo baseReqVo = new BaseReqVo();
        if(delete == 1){
            //删除文件
            String url = storage.getUrl();
            String head = "http://" + request.getServerName() //服务器地址
                    + ":"
                    + request.getServerPort()           //端口号
                    + request.getContextPath();
            String s = url.replaceAll(head, "");
            String realPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static";
            realPath += s;
            File file = new File(realPath);
            file.deleteOnExit();
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("success");
        }else{
            baseReqVo.setErrno(500);
            baseReqVo.setErrmsg("failed");
        }
        return baseReqVo;

    }
}
