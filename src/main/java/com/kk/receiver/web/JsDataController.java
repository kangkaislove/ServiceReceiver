package com.kk.receiver.web;

import com.kk.receiver.storage.StoringJSData;
import com.kk.receiver.utils.ImageUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by kangkai on 2018/1/26.
 */

@RestController
public class JsDataController {

    @RequestMapping(value = {"/api/stat/rt/js"}, method = {RequestMethod.GET})
    @CrossOrigin(origins = "*")
    @ResponseBody
    private String javaScriptCollector(HttpServletRequest httpServletRequest,
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

        System.out.println("**************JavaScript****************");
        System.out.println("appId is:" + request.getParameter("appId"));
        System.out.println(logData);
        System.out.println("**************JavaScript****************");

        if (logData.isEmpty())
            return;
        //添加到本地数组
        StoringJSData.getInstance().addToList(logData);

    }

    private void notice(HttpServletResponse response) {

        // img为图片的二进制流
        byte[] img = ImageUtil.getImageBinary(this.getClass().getResource("/imgs/dog.jpg").getPath());
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