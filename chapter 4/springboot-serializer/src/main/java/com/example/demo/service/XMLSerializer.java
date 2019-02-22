package com.example.demo.service;
import com.example.demo.model.AyUser;
import com.example.demo.service.impl.ISerializer;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * XML序列化
 * @author ay
 * @dare 2019-01-17
 */
public class XMLSerializer implements ISerializer {

    /**
     * 将对象序列化
     */
    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder xe = new XMLEncoder(baos, "utf-8", true, 0);
        xe.writeObject(obj);
        xe.close();
        return baos.toByteArray();
    }

    /**
     * 将二进制对象反序列化
     */
    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        XMLDecoder xd = new XMLDecoder(bais);
        xd.close();
        return (T) xd.readObject();
    }

    @Override
    public <T> void serializeToFile(T obj, String fileName) {

    }

    @Override
    public <T> T deserializeFromFile(String fileName, Class<T> clazz) {
        return null;
    }

    public static void main(String[] args) throws Exception{
        File file = new File("ayUser.xml");
        if(!file.exists()){
            file.createNewFile();
        }
        AyUser ayUser = new AyUser();
        ayUser.setId(1L);
        ayUser.setName("ay");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        XMLEncoder xe = new XMLEncoder(bos);
        xe.flush();
        //写入xml
        xe.writeObject(ayUser);
        xe.close();
        bos.close();

        //读取xml文件
        XMLDecoder xd = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
        AyUser ayUser2 = (AyUser) xd.readObject();
        xd.close();
        System.out.println("id :" + ayUser2.getId());
        System.out.println("name :" + ayUser2.getName());

    }
}
