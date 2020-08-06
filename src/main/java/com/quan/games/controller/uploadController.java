package com.quan.games.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("upload")
public class uploadController {
    @RequestMapping(value = "load", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String upload(@RequestParam("img") String img, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(img);
        if (!file.isEmpty()) {
            file.transferTo(new File(file.getOriginalFilename()));
        }

        return "上传成功";
    }
}
