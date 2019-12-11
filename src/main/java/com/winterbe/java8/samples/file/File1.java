package com.winterbe.java8.samples.file;

import java.io.File;

public class File1 {
    public static void main(String[] args) {
        String filePath = "/tmp";
        File dir = new File(filePath);
        // 列出最近更新的一分钟的文件
        File[] files = dir.listFiles(
                file -> file.isFile() && file.lastModified() > System.currentTimeMillis() - 1_000 * 60);

    }
}
