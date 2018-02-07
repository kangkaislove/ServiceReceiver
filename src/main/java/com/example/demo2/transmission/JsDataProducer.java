package com.example.demo2.transmission;

import com.example.demo2.utils.MyException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Arrays;
import java.util.Properties;

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
