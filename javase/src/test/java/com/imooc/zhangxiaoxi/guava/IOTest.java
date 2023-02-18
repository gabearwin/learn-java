package com.imooc.zhangxiaoxi.guava;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

// 可以参考 https://github.com/junglegodlion/sayBy996/blob/master/README.md
// mavenHelper/GsonFormat/POJO to JSON/CamelCase/GenerateAllSetter/Mybatis Log Plugin


/**
 * 演示如何使用流(Source)与汇(Sink)来对文件进行常用操作
 */
public class IOTest {


    @Test
    public void copyFile() throws IOException {
        /**
         * 创建对应的Source和Sink
         */
        CharSource charSource = Files.asCharSource(new File("README.md"), Charsets.UTF_8);
        CharSink charSink = Files.asCharSink(new File("haha"), Charsets.UTF_8);

        /**
         * 拷贝
         */
        charSource.copyTo(charSink);


    }

}
