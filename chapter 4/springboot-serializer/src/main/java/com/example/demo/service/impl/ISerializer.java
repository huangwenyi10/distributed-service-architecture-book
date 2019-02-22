package com.example.demo.service.impl;

/**
 * 描述：序列化接口
 * @author ay
 * @date 2019-01-27
 */
public interface ISerializer {

    /**
     * 对像序列化
     * @param obj
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T obj);

    /**
     * 对象反序列化
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data, Class<T> clazz);

    /**
     * 将对象序列化到文件中
     * @param obj
     * @param fileName
     * @param <T>
     * @return
     */
    <T> void serializeToFile(T obj, String fileName);


    /**
     * 从文件中反序列化成对象
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserializeFromFile(String fileName, Class<T> clazz);
}
