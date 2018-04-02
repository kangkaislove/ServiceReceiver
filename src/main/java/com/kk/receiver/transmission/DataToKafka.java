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
public class DataToKafka implements Runnable{

    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(RCVConfig.DETECTION_CYCLE * 1000);
                System.out.println("当前数组的长度:" + CachingData.getInstance().getDataLength());
                if(CachingData.getInstance().getDataLength() == RCVConfig.BATCH_COUNT){
                    System.out.println("数据准备上传kafka");
                    sendToKafka(CachingData.getInstance().getData());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendToKafka(List<String> data){
        for (int index = 0; index < data.size(); index++) {
            final int finalIndex = index;
            RCVProducer.getInstance().send(new ProducerRecord<>(Contants.topic_name,data.get(index)), (recordMetadata, e) -> {
                if(finalIndex == data.size()-1 ){
                    System.out.println("数据写入kafka成功");
                    CachingData.getInstance().DeleteData();
                }
            });
        }
    }
}
