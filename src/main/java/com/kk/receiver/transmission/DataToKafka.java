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
                if(CachingData.getInstance().getDataLength() >= RCVConfig.BATCH_COUNT){
                    System.out.println("数据准备上传kafka");
                    sendToKafka(CachingData.getInstance().getData());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendToKafka(List<String> datas){

        Properties props = new Properties();

        //用于建立与 kafka 集群连接的 host/port 组。
        props.put("bootstrap.servers", RCVConfig.KAFKA_SERVERS);
        //需要server接收到数据之后发出的确认接收的信号(值代表需要多少个这样的确认信号)
        props.put("acks", "all");
        //大于0的值将使客户端重新发送任何数据，一旦这些数据发送失败
        props.put("retries", 0);
        //设置缓冲区的大小
        props.put("batch.size", 16384);
        //设置延迟
        props.put("linger.ms", 1);
        //可以用来缓存数据的内存大小
        props.put("buffer.memory", 33554432);
        //key的序列化方式
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //value序列化方式
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String,String> producer = new KafkaProducer<>(props);

        for (int index = 0; index < datas.size(); index++) {
            final int finalIndex = index;
            producer.send(new ProducerRecord<>(Contants.topic_name,index+"",datas.get(index)), (recordMetadata, e) -> {
                if(finalIndex == datas.size()-1 ){
                    System.out.println("数据写入kafka成功");
                    CachingData.getInstance().DeleteData();
                }
            });
        }
    }
}
