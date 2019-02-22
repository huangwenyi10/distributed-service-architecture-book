package com.example.demo.service;
import com.example.demo.model.AyUser;
import com.example.demo.service.impl.ISerializer;
import java.io.*;
import java.util.Arrays;

/**
 * 描述：自定义序列化实现
 * @author ay
 * @date 2019-01-27
 */
public class JavaSerializer implements ISerializer {

    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return
     */
    @Override
    public <T> byte[] serialize(T obj) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return baos.toByteArray();
    }

    /**
     * 反序列化
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T)ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 将对象序列化到文件
     * @param obj
     * @param fileName
     * @param <T>
     */
    @Override
    public <T> void serializeToFile(T obj, String fileName) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 将对象从文件中反序列化
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T deserializeFromFile(String fileName, Class<T> clazz) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            return (T)ois.readObject();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        JavaSerializer javaSerializer = new JavaSerializer();
        AyUser ayUser = new AyUser();
        ayUser.setId(1L);
        ayUser.setName("ay");
        //序列化
        byte[] userBytes = javaSerializer.serialize(ayUser);
        System.out.println("serializer byte data is: " + Arrays.toString(userBytes));
        //反序列化
        AyUser deserializeUser = javaSerializer.deserialize(userBytes, AyUser.class);
        System.out.println("deserialize data is： " + deserializeUser.toString());
        //序列化到文件
        javaSerializer.serializeToFile(ayUser, "tmp.out");
        //从文件中反序列化
        AyUser deserializeFromFileUser = javaSerializer.deserializeFromFile("tmp.out", AyUser.class);
        System.out.println("deserialize from file user : " + deserializeFromFileUser);

    }
}
