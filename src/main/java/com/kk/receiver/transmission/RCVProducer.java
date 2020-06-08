package com.kk.receiver.transmission;

import com.kk.receiver.config.RCVConfig;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

/**
 * @Description : KafkaProducer实例类配置
 * @Author : k.k
 * @Data : 2019/2/20
 */

public class RCVProducer {

    private static KafkaProducer<String,String> producer;//KafkaProducer是线程安全的

    public RCVProducer() {
    }

    public static KafkaProducer<String,String>  getInstance(){
        if(producer == null){
            synchronized (RCVProducer.class){
                if (producer == null){
                    //设置配置文件
                    Properties props = new Properties();

                    //用于建立与 kafka 集群连接的 host/port 组(必须制定)
                    props.put("bootstrap.servers", RCVConfig.KAFKA_SERVERS);
                    //key的序列化方式(必须制定)
                    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                    //value序列化方式(必须制定)
                    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                    //需要server接收到数据之后发出的确认接收的信号(值代表需要多少个这样的确认信号)
                    props.put("acks", "-1");
                    //数据发送失败的重试次数
                    props.put("retries", 3);
                    //设置缓冲区的大小
                    props.put("batch.size", 323840);
                    //设置延迟(10毫秒)
                    props.put("linger.ms", 10);
                    //可以用来缓存数据的内存大小(字节)
                    props.put("buffer.memory", 33554432);
                    //当需要的metadata未到达之前,阻塞的等待时间
                    props.put ("max.block.ms", 3000);
                    //数据压缩类型
                    props.put ("compression.type","lz4");

                    //初始化
                    producer = new KafkaProducer<String,String>(props);
                }
            }
        }
        return producer;
    }


}
