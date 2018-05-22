package com.kk.receiver.storage;

import com.kk.receiver.config.RCVConfig;
import com.kk.receiver.transmission.DataToKafka;

import java.util.*;

/**
 * 缓存数据类
 * Created by kangkai on 2018/1/31.
 */
public class CachingData {

    private static CachingData instance;

    private static List<String> Data = Collections.synchronizedList(new LinkedList<String>());

    public CachingData() {

    }

    public static CachingData getInstance() {
        if (instance == null) {
            synchronized (CachingData.class) {
                if (instance == null) {
                    instance = new CachingData();
                }
            }
        }
        return instance;
    }

    /*
    * 添加数据至数组
    * */
    public void addToList(String data) {
         Data.add(data);
    }

    /*
    * 获取数组内容
    * */
    public List<String> getData() {
      synchronized (Data){
        return Data;
      }
    }

    /*
    * 清除数组内容
    * */
    public void DeleteData() {
        synchronized (Data){
        if (!Data.isEmpty()) {
            Data.clear();
        }
      }
    }

    /*
    * 获取数组的长度
    * */
    public int getDataLength() {
        return Data.size();
    }
}
