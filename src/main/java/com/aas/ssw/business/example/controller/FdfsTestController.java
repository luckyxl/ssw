package com.aas.ssw.business.example.controller;

import com.aas.ssw.common.util.FastDFSClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/file")
public class FdfsTestController {


    // 上传文件
    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "fileName")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileUrl= FastDFSClientUtil.uploadFile(file);
        return fileUrl;
    }
    // 删除文件
    @PostMapping(value = "/delete")
    @ResponseBody
    public String delete(String fileUrl) throws Exception {
        return FastDFSClientUtil.deleteFile(fileUrl);
    }
    @GetMapping(value = "/showPage")
    public String showPage() {
        return "example/file";
    }
}
