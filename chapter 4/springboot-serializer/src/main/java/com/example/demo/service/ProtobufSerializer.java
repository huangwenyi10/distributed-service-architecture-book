package com.example.demo.service;

import com.example.demo.model.AyUserProto;
import com.example.demo.service.impl.ISerializer;

/**
 * 描述：protobuf序列化
 * @author ay
 * @date 2019-01-27
 */
public class ProtobufSerializer implements ISerializer {


    @Override
    public <T> byte[] serialize(T obj) {

        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> void serializeToFile(T obj, String fileName) {

    }

    @Override
    public <T> T deserializeFromFile(String fileName, Class<T> clazz) {
        return null;
    }

    public static void main(String[] args) throws Exception{
        AyUserProto.AyUser ayUser = AyUserProto.AyUser.newBuilder().setId(1L).setName("ay").build();
        //将ayUser对象序列化为ByteString类型的对象
        System.out.println(ayUser.toByteString());
        //将ayUser对象序列化为byte[]
        System.out.println(ayUser.toByteArray());
        //将ByteString类型的对象反序列化为AyUserProto.AyUser对象
        AyUserProto.AyUser newAyUser = AyUserProto.AyUser.parseFrom(ayUser.toByteString());
        System.out.println(newAyUser);
        //将toByteArray类型的对象反序列化为AyUserProto.AyUser对象
        AyUserProto.AyUser newAyUser2 = AyUserProto.AyUser.parseFrom(ayUser.toByteArray());
        System.out.println(newAyUser2);
    }
}
