package com.yed.controller;

import com.yed.utils.DownFileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("fileup")
@CrossOrigin()
public class FileUpController {

    @Value("${file.path}")
    private String filePath;

    @Value("${file.domain}")
    private String fileDomain;


    @PostMapping("upfile")
    public Object uploadFile(@RequestParam(defaultValue = "filename") MultipartFile file){
        String fname = UUID.randomUUID() + ".jpg";
        File file1 = new File(filePath + fname);
        Map<String,Object> remap = new HashMap<>();
        remap.put("result",false);
        try {
            file.transferTo(file1);
            remap.put("result",true);
            remap.put("fileName",fname);
            remap.put("filePathName",fileDomain+fname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return remap;
    }


    @GetMapping("del")
    public Object delFile(String fileName){
        File file = new File(filePath + fileName);
        boolean b = file.delete();
        return b;
    }

    /*@GetMapping("loadpic")
    private int LoadPic(String fileName, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("E:\\pic\\"+fileName+"*******************");

            //response.setHeader("Access-Control-Allow-Origin", "http://localhost");
            DownFileUtils.download(fileName,"E:\\pic\\",response);
            return 1;

    }*/
}
