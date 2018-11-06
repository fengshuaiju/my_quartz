package com.feng.util;

import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@NoArgsConstructor
public class DownLoadImages implements Runnable{

    //资源下载之后，保存在本地的文件路径
    private String basePath = "/Users/jyd/Downloads/";

    private String imageUrl;
    private String fileName;
    private String tittle;

    public DownLoadImages(String tittle, String imageUrl, String fileName){
        this.tittle = tittle;
        this.imageUrl = imageUrl;
        this.fileName = fileName;
        this.basePath = this.basePath + this.tittle + "/";
    }


    /**
     * 根据图片的外网地址下载图片到本地硬盘的filePath
     */
    @Override
    public void run() {
        try {
            //创建文件目录
            File files = new File(basePath);
            if (!files.exists()) {
                files.mkdirs();
            }
            //获取下载地址
            URL url = new URL(this.imageUrl);

            //链接网络地址
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            //获取链接的输出流
            InputStream is = connection.getInputStream();
            //创建文件，fileName为编码之前的文件名
            File file = new File(basePath + fileName);
            //根据输入流写入文件
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            out.close();
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
