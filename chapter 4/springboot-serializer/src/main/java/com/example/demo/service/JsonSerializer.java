package com.example.demo.service;
import com.alibaba.fastjson.JSON;
import com.example.demo.service.impl.ISerializer;

/**
 * 描述:json序列化
 * @author ay
 * @date 2019-01-17
 */
public class JsonSerializer implements ISerializer {

    @Override
    public <T> byte[] serialize(T obj) {
        //将对象序列化成二进制数组
        return JSON.toJSONString(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        //将二进制数组反序列化成对象
        return (T)JSON.parseObject(new String(data));
    }

    @Override
    public <T> void serializeToFile(T obj, String fileName) {

    }

    @Override
    public <T> T deserializeFromFile(String fileName, Class<T> clazz) {
        return null;
    }

}
