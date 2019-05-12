package com.example.demo;


import java.io.File;

/**
 * 描述：PowerMock测试
 *
 * @author ay
 * @date 2019-05-01
 */
public class PowerMockTest {


    public void testCheckFile(){

    }

}


class FileService{

    public boolean checkFile(File file) {
        return file.exists();
    }
}
