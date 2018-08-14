package com.zhu.tools.datasource;


/**
 * Created by ZHU on 2018/8/13.
 */
public class HandlerDataSource {
    private static final ThreadLocal<String> handlerThredLocal  = new ThreadLocal<String>();

    public static void putDataSource(String datasource){
        handlerThredLocal.set(datasource);
    }

    public static String getDataSource(){
        return handlerThredLocal.get();
    }

    public static void clear(){
        handlerThredLocal.remove();
    }
}
