package com.kk.receiver.collector;

import com.kk.receiver.service.AsyncService;
import com.kk.receiver.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description : 网页端数据接收处理
 * @Author : k.k
 * @Data : 2019/2/20
 */

@RestController
public class WebPageEndCollector {

    @Autowired
    private AsyncService service;

    @RequestMapping(value = {"/api/stat/rt/js"}, method = {RequestMethod.GET})
    @ResponseBody
    private String WebPageEndReceiver(HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) {

        //处理数据
        dealData(httpServletRequest);

        //返回图片二进制,告诉客户端是否上传数据是否成功
        notice(httpServletResponse);

        return "{\"result\":1}";
    }

    private void dealData(HttpServletRequest request) {

        String logData = "";

        //打印get请求url中带过来的参数
        logData = request.getParameter("log");

        System.out.println("**************JavaScript——head****************");
        System.out.println("appId is:" + request.getParameter("appId"));
        System.out.println(logData);
        System.out.println("**************JavaScript——foot****************");

        if (logData.isEmpty()){
            return;
        }
        //添加到本地缓存中
        service.executeAsync(logData);

    }

    private void notice(HttpServletResponse response) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("dog.jpg");
        // img为图片的二进制流
        byte[] img = ImageUtil.input2byte(stream);
        response.setContentType("image/jpg");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            os.write(img);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}