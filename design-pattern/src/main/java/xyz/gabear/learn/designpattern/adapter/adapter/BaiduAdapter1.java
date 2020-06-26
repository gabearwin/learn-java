package xyz.gabear.learn.designpattern.adapter.adapter;

import xyz.gabear.learn.designpattern.adapter.NetEaseInterface;
import xyz.gabear.learn.designpattern.adapter.adaptee.BaiduApi;

// 对象适配器 组合类
public class BaiduAdapter1 implements NetEaseInterface {

    @Override
    public void doNetEaseGet() {
        System.out.println("使用百度适配器 1 ");
        BaiduApi baiduApi = new BaiduApi();
        baiduApi.doBaiduGet();
    }
}
