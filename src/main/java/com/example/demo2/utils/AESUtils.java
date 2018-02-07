package com.example.demo2.utils;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>
 * AES加密解密工具包
 * </p>
 *
 * @date 2012-5-18
 * @version 1.0
 */
public class AESUtils {

    private static final String ALGORITHM = "AES";
    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};


    public static byte[] encryptByteContent(byte[] content,String secretKey) {
        try {
            final byte[] outBytes = AESUtils.encrypt(content, MessageDigest.getInstance("MD5").digest(secretKey.getBytes("UTF-8")));
            return outBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static byte[] gZByteContent(String content) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(content.getBytes("UTF-8"));
            gzip.finish();
            gzip.close();
            b = bos.toByteArray();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }




    public static String getSig(Map<String, String> paramMap,String secretKey) {
        Vector<String> vecSig = new Vector<String>();
        for (String key : paramMap.keySet()) {
            String value = paramMap.get(key);
            vecSig.add(key + "=" + value);
        }

        String[] nameValuePairs = new String[vecSig.size()];
        vecSig.toArray(nameValuePairs);

        for (int i = 0; i < nameValuePairs.length; i++) {
            for (int j = nameValuePairs.length - 1; j > i; j--) {
                if (nameValuePairs[j].compareTo(nameValuePairs[j - 1]) < 0) {
                    String temp = nameValuePairs[j];
                    nameValuePairs[j] = nameValuePairs[j - 1];
                    nameValuePairs[j - 1] = temp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nameValuePairs.length; i++) {
            sb.append(nameValuePairs[i]);
        }
        sb.append(secretKey);

        return MD5Encode(sb.toString());
    }



    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(origin.getBytes("UTF-8")));
        }
        catch (Exception ex) {

        }
        return resultString;
    }


    public static String MD5Encode(byte[] origin) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(origin));
        }
        catch (Exception ex) {

        }
        return resultString;
    }

    /**
     * <p>
     * 加密
     * </p>
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        byte[] raw = k.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    /**
     * <p>
     * 转换密钥
     * </p>
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        return secretKey;
    }



    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n >>> 4 & 0xf;
        int d2 = n & 0xf;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**解密
     * @param content  待解密内容
     * @param key 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String secretKey) throws Exception{

            byte[] key = MessageDigest.getInstance("MD5").digest(secretKey.getBytes("UTF-8"));
            Key k = toKey(key);
            byte[] raw = k.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密

    }

}