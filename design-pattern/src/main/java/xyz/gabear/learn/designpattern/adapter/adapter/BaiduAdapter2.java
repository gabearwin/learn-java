package xyz.gabear.learn.designpattern.adapter.adapter;

import xyz.gabear.learn.designpattern.adapter.NetEaseInterface;
import xyz.gabear.learn.designpattern.adapter.adaptee.BaiduApi;

// 类适配器
public class BaiduAdapter2 extends BaiduApi implements NetEaseInterface {

    @Override
    public void doNetEaseGet() {
        System.out.println("使用百度适配器 2 ");
        this.doBaiduGet();
    }
}
