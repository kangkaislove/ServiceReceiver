package com.kk.receiver.config;

/**
 * Created in 2018/3/20
 * author:k.k
 * function:接收服务的配置文件
 */
public class RCVConfig {

     public static final int DETECTION_CYCLE = 5 ;//设置定时检测周期,秒

     public static final int BATCH_COUNT = 10 ;//设置缓存数据条数的阈值,达到该标准即进行发往kafka

     public static final String KAFKA_SERVERS = "master:9092,slave1:9092,slave2:9092";//配置kafka集群
}
