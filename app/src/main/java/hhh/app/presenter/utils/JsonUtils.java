package hhh.app.presenter.utils;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * Created by hhh on 2016/6/12.
 */
public class JsonUtils {
//    public static String toJson(Object obj){
//        return new Gson().toJson(obj);
//    }
//
//    public static<T> T fromJson(String json,Type type){
//        //Type t=new TypeToken<T>(){}.getType();
//        return new Gson().fromJson(json,type);
//    }

    public static String toJson(Object obj){
        return JSON.toJSONString(obj);
    }

    public static<T> T fromJson(String json,Type type){
        //Type t=new TypeToken<T>(){}.getType();
        return JSON.parseObject(json,type);
    }
}
