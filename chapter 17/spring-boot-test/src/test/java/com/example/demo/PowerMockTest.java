package com.example.demo;


import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * 描述：PowerMock测试
 *
 * @author ay
 * @date 2019-05-01
 */
public class PowerMockTest {




}


class FileService{

    public boolean checkFile(File file) {
        return file.exists();
    }
}
