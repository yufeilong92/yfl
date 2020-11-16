package com.backpacker.yflLibrary.java;

import java.io.*;
/**
 * @Author : YFL  is Creating a porject in Dell
 * @Package :
 * @Email : yufeilong92@163.com
 * @Time :2020/11/16 13:44
 * @Purpose :io 工具类
 */
public class JavaIoUtils {

    private static final int BUFFER_SIZE = 1024 * 2;

    private JavaIoUtils() {
        // Utility class.
    }

    public static int copy(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {

            }
            try {
                in.close();
            } catch (IOException e) {

            }
        }
        return count;
    }


}
