package com.zoro.springboot.controller;

import com.zoro.springboot.constant.ResEnum;
import com.zoro.springboot.constant.ResultRes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @date 2018/8/30  9:35
 */
@RestController
public class UploadController {

    @RequestMapping("/doUpload")
    public ResultRes uploadSingleFile(HttpServletRequest request, MultipartFile file){
        //上传地址
        String path = request.getSession().getServletContext().getRealPath("/")+"upload/";
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        return upload(path,file);
    }

    @RequestMapping("/doUploads")
    public ResultRes uploadMultiFile(HttpServletRequest request, MultipartFile[] file){
        //上传地址
        String path = request.getSession().getServletContext().getRealPath("/")+"upload/";
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        for (int i = 0; i < file.length; i++) {
            ResultRes res = upload(path,file[i]);
            if(!res.getMessage().equals(ResEnum.SUCCESS.toString())){
                return res;
            }
        }
        return ResultRes.success("上传成功");
    }

    private ResultRes upload(String path,MultipartFile file){
        //上传文件名
        String subffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //String filename = file.getOriginalFilename();
        //String filename = System.nanoTime()+subffix;  //使用微毫秒作为文件名
        String filename = UUID.randomUUID()+subffix;
        File serverFile = new File(path+filename);
        //将上传的文件保存到本地服务器
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultRes.buinessError(500,"上传失败");
        }
        return ResultRes.success("上传成功");
    }
}
