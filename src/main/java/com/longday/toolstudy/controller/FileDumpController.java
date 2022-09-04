package com.longday.toolstudy.controller;

import com.longday.toolstudy.service.FdFileListService;
import com.longday.toolstudy.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 君
 * @version 1.0
 * @date 2022/8/2 14:09
 */
@RestController
@RequestMapping("/file")
public class FileDumpController {
    @Autowired
    private FdFileListService fdFileListService;

    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile file){
        if(file == null){
            System.out.println("upload");
            return ResponseResult.errorResult(303,"空文件");
        }else {
            System.out.println("fill不为null");
        }

        boolean fileIsExists = fdFileListService.isAlreadyExists();
        if (fileIsExists){
            //文件已存在直接返回上传成功
            System.out.println("判断文件是否已存在");
            return ResponseResult.okResult(200,"上传成功");
        }
        //接收上传的文件
        //获取前端文件名
        String originalFilename = file.getOriginalFilename();
        String  todayDate=DateTimeFormatter.ofPattern("yyyy-MM-dd").format( LocalDateTime.now());
        String path;
        try {
             path = ResourceUtils.getURL("classpath:").getPath()+ "static/files";
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //处理根据日期生成目录
        String dateDirPath = path + "/" + todayDate;
        File dataDir = new File(dateDirPath);
        if (!dataDir.exists()){
            dataDir.mkdirs();
        }
        File file1 = new File(dateDirPath+"/"+originalFilename);
        System.out.println(dateDirPath+originalFilename);

        try {
            file.transferTo(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //添加到数据库
        fdFileListService.upload();
        return ResponseResult.okResult(200,"上传成功");
    }

    @RequestMapping("/filesList")
    public ResponseResult getFilesList(){
        return fdFileListService.getFilesList();
    }
}
