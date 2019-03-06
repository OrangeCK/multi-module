package com.ck.multimodulecommon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @author ck
 * @date 2019/1/4 13:48
 * Description  : 上传工具类
 */
public class UploadUtil {
    private final static Logger logger = LoggerFactory.getLogger(UploadUtil.class);

    /**
     * 上传文件
     * @param multipartFile 文件
     * @param path 路径
     * @param rootFileName 文件名
     * @return
     */
    public static String uploadFile(MultipartFile multipartFile, String path, String rootFileName) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        // 获取文件的后缀
        String suffix = rootFileName.substring(rootFileName.lastIndexOf("."));
        // 获取时间戳
        long currTimestamp = System.currentTimeMillis();
        // 给文件重命名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + currTimestamp + suffix;
        // 输出流
        FileOutputStream fo = new FileOutputStream(path + File.separator + fileName);
        BufferedOutputStream bo = new BufferedOutputStream(fo);
        // 定义字节缓冲区，减少I/O次数，提高读写效率
        byte[] but = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(but)) != -1){
            // 简单来说就是将数组but的len个字节按顺序写入输入流，0是偏移量，就是说从0开始的len个字节
            bo.write(but, 0, len);
        }
        // flush的作用是写入磁盘，调用close之前，默认会flush;
        bo.flush();
        bo.close();
        fileInputStream.close();
        fo.close();
        return fileName;
    }

    /**
     * 下载文件
     * @param request 请求
     * @param response 响应
     */
    public static void downloadFile(HttpServletResponse response, String fileName, String filePath){
        File file = new File(filePath);
        // 为了防止客户端浏览器直接打开目标文件,设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        byte[] but = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int len = 0;
            while(-1 != (len = bis.read(but, 0 ,but.length))){
                os.write(but, 0, len);
            }
        } catch (FileNotFoundException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            logger.error("文件没有找到，文件下载失败" + sw.toString());
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            logger.error("文件下载失败" + sw.toString());
        }finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw, true));
                    logger.error("bis流关闭错误，文件下载失败" + sw.toString());
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw, true));
                    logger.error("fis流关闭错误，文件下载失败" + sw.toString());
                }
            }
        }
    }

    /**
     * 删除文件
     * @param file 文件
     * @return
     */
    public static boolean deleteFile(File file){
        // 判断是否是文件夹
        if(file.isDirectory()){
            String[] childrenList = file.list();
            for(int i = 0; i < childrenList.length; i++){
                // 递归删除目录下的子目录
                deleteFile(new File(file, childrenList[i]));
            }
        }
        // 目录为空，可以删除
        return file.delete();
    }
}
