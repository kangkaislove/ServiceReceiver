package com.kk.receiver.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存数据类
 * Created by kangkai on 2018/1/31.
 */
public class CachingData {

    private static CachingData instance;

    volatile private static List<String> jsDatas = new ArrayList<>();

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
        jsDatas.add(data);
    }

    /*
    * 获取数组内容
    * */
    public List<String> getData() {
        return jsDatas;
    }

    /*
    * 清除数组内容
    * */
    public void DeleteData() {
        if (jsDatas != null && jsDatas.size() > 0) {
            jsDatas.clear();
        }
    }

    /*
    * 获取数组的长度
    * */
    public int getDataLength() {
        return jsDatas.size();
    }
}
