package com.kk.receiver.config;

/**
 * Created in 2018/3/20
 * function:接收服务的配置文件
 * @author kangkai
 */
public class RCVConfig {

     /**配置kafka集群*/
     public static final String KAFKA_SERVERS = "master:9092,slave1:9092,slave2:9092";

     /**网页采集数据对应kafka的TopicName*/
     public final static String TOPIC_NAME = "Alex";
}
