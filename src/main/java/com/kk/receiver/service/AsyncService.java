package com.kk.receiver.service;

/**
 * @Description : 执行异步任务的接口
 * @Author : k.k
 * @Data : 2019/2/20
 */

public interface AsyncService {
     /**
      * 执行异步任务
      * @param data 发送的数据
      */
     void executeAsync(String data);
}
