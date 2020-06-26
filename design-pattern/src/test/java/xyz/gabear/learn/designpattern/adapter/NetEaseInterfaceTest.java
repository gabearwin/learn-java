package xyz.gabear.learn.designpattern.adapter;

import org.junit.Test;
import xyz.gabear.learn.designpattern.adapter.adapter.BaiduAdapter1;
import xyz.gabear.learn.designpattern.adapter.adapter.BaiduAdapter2;

public class NetEaseInterfaceTest {

    @Test
    public void doGet() {
        NetEaseInterface netEase = new BaiduAdapter1();
        netEase.doNetEaseGet();
        System.out.println("\n***********\n");
        netEase = new BaiduAdapter2();
        netEase.doNetEaseGet();
    }

    /*使用百度适配器 1
    成功调用百度API

    ***********

    使用百度适配器 2
    成功调用百度API*/

}