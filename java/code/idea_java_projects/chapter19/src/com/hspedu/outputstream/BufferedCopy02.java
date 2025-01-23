package com.hspedu.outputstream;

import java.io.*;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class BufferedCopy02 {
    public static void main(String[] args) {

        String srcFilePath = "D:\\music.jpg";
        String destFilePath = "D:\\music2.jpg";

        //创建BufferedInputStream对象、BufferedOutputStream对象
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //因为FileInputStream是InputStream子类
            bis = new BufferedInputStream(new FileInputStream(srcFilePath));
            bos = new BufferedOutputStream(new FileOutputStream(destFilePath));

            //循环的读取文件，并写入到destFilePath
            byte[] buff = new byte[1024];
            int readLen = 0;

            //当返回-1时，就表示文件读取完毕
            while ((readLen = bis.read(buff)) != -1) {
                bos.write(buff, 0, readLen);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭流，关闭外层的处理流即可，底层会去关闭节点流
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
