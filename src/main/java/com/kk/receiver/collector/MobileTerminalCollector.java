package com.kk.receiver.collector;

import com.kk.receiver.config.AppSecretKeyConfig;
import com.kk.receiver.service.AsyncService;
import com.kk.receiver.utils.AESUtils;
import com.kk.receiver.utils.GZIPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.InputStream;

/**
 * @Description : 客户端数据接收处理
 * @Author : k.k
 * @Data : 2019/2/20
 */

@RestController
public class MobileTerminalCollector {

    @Autowired
    private AsyncService service;

    @RequestMapping(value = "/api/stat/rt",method = {RequestMethod.POST})
    @ResponseBody
    private String mobileTerminalReceiver(HttpServletRequest request){

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
//            System.out.println("数据的长度:" + totalBytes);
            is = request.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(is);
            byte[] bytes = new byte[totalBytes];
            dataInputStream.readFully(bytes);
            dataInputStream.close();

            System.out.println("appId is:" + request.getParameter("appId"));
            //先进行AES解密二进制流数据
            byte[] decryptByte = AESUtils.decrypt(bytes, AppSecretKeyConfig.SECRET_KEY);
            //再进行GZIP解压
            byte [] resultByte = GZIPUtils.uncompress(decryptByte);
            //得到完整的数据
            String data =new String(resultByte,"UTF-8").trim();
            //打印输出
            System.out.println("**************移动端****************");
            System.out.println("appId is:" + request.getParameter("appId"));
            System.out.println(data);
            System.out.println("**************移动端****************");

            //添加到本地缓存中
            service.executeAsync(data);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
}
