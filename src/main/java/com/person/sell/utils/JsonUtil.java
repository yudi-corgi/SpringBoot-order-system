package com.person.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 将Java对象输出转换为JSON格式
 */

public class JsonUtil {

    public static String toJSON(Object object){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }


}
