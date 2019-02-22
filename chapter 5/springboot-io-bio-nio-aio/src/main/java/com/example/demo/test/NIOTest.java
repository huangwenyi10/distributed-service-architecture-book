package com.example.demo.test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

    public static void main(String[] args) throws Exception{

          //实例一
//        RandomAccessFile aFile = new RandomAccessFile("/Users/ay/Desktop/ay.log", "rw");
//        FileChannel inChannel = aFile.getChannel();

//        ByteBuffer buf = ByteBuffer.allocate(48);
//        int bytesRead = inChannel.read(buf);
//
//        System.out.println("read bytesRead :" + bytesRead);

        //实例二
        RandomAccessFile aFile = new RandomAccessFile("/Users/ay/Desktop/ay.log", "rw");
        FileChannel inChannel = aFile.getChannel();

        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while(buf.hasRemaining()) {
            inChannel.write(buf);
        }

        inChannel.close();

    }
}
