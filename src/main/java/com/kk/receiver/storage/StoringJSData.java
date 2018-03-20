package com.kk.receiver.storage;

import com.kk.receiver.beans.JsData;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 存储网页采集数据至内存中
 * Created by kangkai on 2018/1/31.
 */
public class StoringJSData {

    private static StoringJSData instance;

    volatile private static List<JsData> jsDatas =new ArrayList<>();

    public StoringJSData() {

    }

    public static StoringJSData getInstance(){

        if (instance == null){
             synchronized (StoringJSData.class){
                 if (instance == null){
                     instance =new StoringJSData();
                 }
             }
        }

        return instance;
    }

    /*
    * 添加数据至数组
    * */
    public void addToList(JsData data){
        jsDatas.add(data);
    }


    /*
    * 获取数组内容
    * */
    public List<JsData> getData(){
        return jsDatas;
    }

    /*
    * 清除数组内容
    * */
    public void DeleteData(){
        if( jsDatas != null && jsDatas.size() > 0 ){
           jsDatas.clear();
        }
    }

    /*
    * 获取数组的长度
    * */
    public int getDataLength(){
        return jsDatas.size();
    }
}
