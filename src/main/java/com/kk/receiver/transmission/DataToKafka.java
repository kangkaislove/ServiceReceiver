package com.kk.receiver.transmission;

import com.kk.receiver.config.RCVConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author kangkai
 * @date 2018/1/31
 */
public class DataToKafka {

    public  static void sendToKafka(String data){
            RCVProducer.getInstance().send(new ProducerRecord<>(RCVConfig.TOPIC_NAME, data), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {

                }
            });
    }
}
