package com.payment.controller;



import com.payment.common.BaseContext;
import com.payment.common.CustomException;
import com.payment.common.Result;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${reggie.path}")
    private String BasePath;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        if (BaseContext.getCurrentId() == -1) {
            throw new CustomException("失败！");
        }
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String substring = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String filename = UUID.randomUUID() + substring;
        File dir = new File(BasePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        file.transferTo(new File(BasePath + filename));
        return Result.success(filename);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException{
        if (BaseContext.getCurrentId() == -1) {
            throw new CustomException("失败！");
        }
        FileInputStream inputStream = new FileInputStream(new File(BasePath+name));
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/jpg");
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
            outputStream.flush();
        }
        outputStream.close();
        inputStream.close();
    }
}
