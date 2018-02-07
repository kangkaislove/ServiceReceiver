package com.example.demo2.web;

import com.example.demo2.utils.AESUtils;
import com.example.demo2.utils.Contants;
import com.example.demo2.utils.GZIPUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kangkai on 2018/1/28.
 */

@RestController
public class AndroidAndSwiftDataController {

    @RequestMapping(value = "/api/stat/rt",method = {RequestMethod.POST})
    @ResponseBody
    private String androidCollector(HttpServletRequest request){

        //处理数据
        dealData(request);

        return "{\"result\":1}";
    }

    private void dealData(HttpServletRequest request){

        //获取二进制流数据
        StringBuffer sb = new StringBuffer() ;
        InputStream is = null;
        try {

            int totalBytes = request.getContentLength();
            is = request.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(is);
            byte[] bytes = new byte[totalBytes];
            dataInputStream.readFully(bytes);
            dataInputStream.close();

            try {

                //先进行AES解密二进制流数据
                byte[] decryptByte = AESUtils.decrypt(bytes, Contants.secretKey);
                //再进行GZIP解压
                byte [] resultByte = GZIPUtils.uncompress(decryptByte);
                //打印输出
                System.out.println("**************移动端****************");
                System.out.println(new String(resultByte,"UTF-8").trim());
                System.out.println("**************移动端****************");

            } catch (Exception e) {

                e.printStackTrace();
            }

            } catch (IOException e) {

                e.printStackTrace();
            }

        }
}
