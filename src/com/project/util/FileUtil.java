package com.project.util;

import java.io.*;

/**
 * 文件工具类
 */
public class FileUtil {

    /**
     * 拷贝文件
     * @param sourceFilePath 源文件路径
     * @param targetDirPath 目标文件所在的目录
     * @return 重命名文件
     */
    public static String copyFile(String sourceFilePath, String targetDirPath){
//        重命名文件名
        String fileName = System.currentTimeMillis() + sourceFilePath.substring(sourceFilePath.lastIndexOf("."));

        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(sourceFilePath);
            out = new FileOutputStream(targetDirPath + "/" + fileName);

            byte[] by = new byte[1024];
            int len = 0;

            while ((len = in.read(by))!= -1) {
                out.write(by, 0, len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return fileName;
    }
}
