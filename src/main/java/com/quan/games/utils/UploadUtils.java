package com.quan.games.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

public class UploadUtils {
    public static final String SAVE_IMG_FILE = "images";

    public static String saveImage(MultipartFile multipartFile) throws IOException {
        // 通过配置文件获取保存的路径  注意上线的时候要修改成服务器保存的绝对路径
        ResourceBundle bundle = ResourceBundle.getBundle("sys");
        //获取到了跟路径
        String rootPath = bundle.getString("root.upload.path");
        // 获取保存图片名字的统一前缀
        String imgSuffix = bundle.getString("img.suffix");
        // 生成图片保存路径 根据日期保存
        String imagePath = String.format(SAVE_IMG_FILE, File.separator, DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        /**
         * 保存到服务器数据库的路径
         */
        String savePath = String.format(imagePath, File.separator, getFileName(imgSuffix, multipartFile.getOriginalFilename()));
        // 拼接保存的路径
        //     根路劲  +  业务需求的路径 +  文件名
        File imgFile = new File(String.format(rootPath, File.separator, savePath));
        // 创建保存图片的文件
        FileUtils.touch(imgFile);
        // 将客户端图片写到文件中
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), imgFile);
        return savePath;
    }

    /**
     * 获取
     *
     * @param oldName
     * @return
     */
    public static String getFileName(String suffixName, String oldName) {
        // 对图片进行重命名
        String prefix = suffixName + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        String suffix = oldName.substring(oldName.indexOf("."));
        return prefix + suffix;
    }
}

