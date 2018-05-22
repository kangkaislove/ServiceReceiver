package com.kk.receiver.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Encoder;

/**
 * Created by kangkai on 2018/1/26.
 */
public class ImageUtil {
    /**
     * 将图片转换成二进制
     * @return
     */
    public static byte[] getImageBinary(String image) {
        File f = new File(image);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真
            byte[] bytes = baos.toByteArray();

            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final byte[] input2byte(InputStream inStream) {
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = inStream.read(buff, 0, 1024)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] in2b = swapStream.toByteArray();
            return in2b;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
