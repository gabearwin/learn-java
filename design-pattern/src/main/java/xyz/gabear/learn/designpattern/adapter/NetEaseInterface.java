package xyz.gabear.learn.designpattern.adapter;

/**
 * 现在我们在网易，需要并且只能访问网易的一个接口来获取数据。
 * 而真正提供数据的接口来自百度，或者美团，所以需要为百度或者美团的接口做适配器模式。
 */
public interface NetEaseInterface {
    void doNetEaseGet();
}
