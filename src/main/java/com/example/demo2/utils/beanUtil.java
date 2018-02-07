package com.example.demo2.utils;

import java.io.*;

/**
 * Created by kangkai on 2018/1/31.
 */
public class beanUtil {

   /* @Description 对象转字节数组
    * @param obj Object对象
    * @return byte[] 字节数组
    * @author kangkai
    * @date 2018/1/31
    */
    public static byte[] ObjectToBytes(Object obj){
        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bo!=null){
                    bo.close();
                }
                if(oo!=null){
                    oo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * @Description 字节数组转对象
     * @param bytes 字节数组
     * @return Object Object对象
     * @author kangkai
     * @date 2018/1/31
     */
    public static Object BytesToObject(byte[] bytes){
        Object obj = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi =new ByteArrayInputStream(bytes);
            oi =new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bi!=null){
                    bi.close();
                }
                if(oi!=null){
                    oi.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
