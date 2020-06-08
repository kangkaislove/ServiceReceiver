package com.kk.receiver;

import com.kk.receiver.config.AppSecretKeyConfig;
import com.kk.receiver.utils.AESUtils;

/**
 *  测试类
 * @author kangkai
 * @date 2018/7/18
 */
public class Test {

    public static void main(String[] args){

        String content = "Secret message";
        //先进行AES解密二进制流数据
        try {
            System.out.println("加密前：" + content);

            //加密
            byte[] decryptByte = AESUtils.encryptByteContent(content.getBytes(), AppSecretKeyConfig.SECRET_KEY);
            String encryptResultStr = parseByte2HexStr(decryptByte);
            System.out.println("加密后：" + encryptResultStr);

            //解密
            byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
            byte[] decryptResult = AESUtils.decrypt(decryptFrom,AppSecretKeyConfig.SECRET_KEY);
            System.out.println("解密后：" + new String(decryptResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
