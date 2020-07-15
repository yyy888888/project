package com.yed.utils;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 杨恩典
 * 版权所有 违者必究
 */
public class DownFileUtils {
    public static void download(String filename, String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path+filename);
            // 取得文件名。
            // 如果文件不存在
            if (!file.exists()) {
                response.sendError(404, "File not found!");
            }
            // 创建一个缓冲输入流对象
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[10240];
            int len = 0;
            response.reset(); // 非常重要
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            // 创建输出流对象
            OutputStream outStream = response.getOutputStream();
            // 开始输出
            while ((len = br.read(buf)) > 0)
                outStream.write(buf, 0, len);
            // 关闭流对象
            br.close();
            outStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
