package com.course.springboot2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@CrossOrigin
@RestController
public class DownLoadFileController {
    @RequestMapping("/downLoadFile")
    public String downLoadFile(HttpServletResponse response) throws Exception {
        File file = new File("C:\\Users\\ASUS\\Desktop\\图片\\5.jpg");
        if (!file.exists()) {
            return "下载文件不存在";
        }
        // 设置响应信息
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        // 设置编码格式
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] buff = new byte[1024];
        OutputStream os = response.getOutputStream();
        int i = 0;
        while ((i = bis.read(buff)) != -1) {
            os.write(buff, 0, i);
            os.flush();
        }
        bis.close();
        os.close();
        return "下载成功";
    }

    @RequestMapping("/upLoadFile")
    public String upLoadFile(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "上传失败";
        }

        System.out.println("上传文件的的名称：" + file.getOriginalFilename());
        System.out.println("上传文件的的大小：" + file.getSize());

        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file.getOriginalFilename()));
        os.write(file.getBytes());
        os.flush();
        os.close();
        return "上传成功";
    }
}
