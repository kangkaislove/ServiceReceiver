package com.kk.receiver.service;

import com.kk.receiver.config.RCVConfig;
import com.kk.receiver.transmission.RCVProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description : 异步任务的实现
 * @Author : k.k
 * @Data : 2019/2/20
 */

@Service
public class AsyncServiceImp implements AsyncService {

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(String data) {

        //使用单例producer发送消息
        RCVProducer.getInstance().send(new ProducerRecord<>(RCVConfig.TOPIC_NAME, data));
    }
}
