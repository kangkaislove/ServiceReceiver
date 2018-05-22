package com.kk.receiver.service;

import com.kk.receiver.storage.CachingData;
import com.kk.receiver.transmission.DataToKafka;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by kangkai on 2018/3/29.
 */


@Service
public class AsyncServiceImp implements AsyncService{

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(String data) {
        DataToKafka.sendToKafka(data);
    }
}
