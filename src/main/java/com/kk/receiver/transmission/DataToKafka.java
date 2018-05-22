package com.kk.receiver.transmission;

import com.kk.receiver.config.RCVConfig;
import com.kk.receiver.storage.CachingData;
import com.kk.receiver.utils.Contants;
import org.apache.kafka.clients.producer.*;

import java.util.List;
import java.util.Properties;

/**
 * Created by kangkai on 2018/1/31.
 */
public class DataToKafka {

    public  static void sendToKafka(String data){
            RCVProducer.getInstance().send(new ProducerRecord<>(Contants.topic_name, data), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {

                }
            });
    }
}
