package com.kk.receiver.config;

/**
 * @Description : 常量配置文件
 * @Author : k.k
 * @Data : 2019/2/20
 */

public class RCVConfig {

     /**配置kafka集群*/
     public static final String KAFKA_SERVERS = "master:9092,server71:9092,server32:9092";

     /**网页采集数据对应kafka的TopicName*/
     public final static String TOPIC_NAME = "kangkai-topic";
}
