package com.kk.receiver.transmission;

import com.kk.receiver.config.RCVConfig;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class RCVProducer {

    private static KafkaProducer<String,String> instance;

    public RCVProducer() {
    }

    public static KafkaProducer<String,String>  getInstance(){
        if(instance == null){
            synchronized (RCVProducer.class){
                if (instance == null){
                    //设置配置文件
                    Properties props = new Properties();
                    //key的序列化方式
                    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                    //value序列化方式
                    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                    //需要server接收到数据之后发出的确认接收的信号(值代表需要多少个这样的确认信号)
                    props.put("acks", "all");
                    //用于建立与 kafka 集群连接的 host/port 组。
                    props.put("bootstrap.servers", RCVConfig.KAFKA_SERVERS);
                    //可以用来缓存数据的内存大小(字节)
                    props.put("buffer.memory", 33554432);
                    //大于0的值将使客户端重新发送任何数据，一旦这些数据发送失败
                    props.put("retries", 0);
                    //设置缓冲区的大小
                    props.put("batch.size", 16384);
                    //设置延迟
                    props.put("linger.ms", 0);


                    //初始化
                    instance = new KafkaProducer<String,String>(props);
                }
            }
        }
        return instance;
    }


}
