package com.kk.receiver.transmission;

import com.kk.receiver.utils.MyException;

/**
 * Created by kangkai on 2018/1/31.
 */
public class JsDataProducer {

    private String topicName;

    public JsDataProducer(String topicName) {
        if(topicName.isEmpty()){
            try {
                throw new MyException("kafka topicName is null.");
            } catch (MyException e) {
                e.printStackTrace();
            }
            return;
        }
        topicName = topicName;
    }


}
