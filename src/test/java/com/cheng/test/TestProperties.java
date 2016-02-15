package com.cheng.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Desc:
 * Author: Cheng
 * Date: 2016/2/15 0015
 */
public class TestProperties {

    public static void main(String[] args) throws IOException {
        InputStream is = TestProperties.class.getResourceAsStream("/spider.properties");
        Properties properties = new Properties();
        properties.load(is);
        String connUrl = properties.getProperty("connUrl");
        System.out.println(connUrl);
    }
}
