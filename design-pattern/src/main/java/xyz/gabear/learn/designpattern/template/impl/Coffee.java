package xyz.gabear.learn.designpattern.template.impl;

import xyz.gabear.learn.designpattern.template.BeverageTemplate;

public class Coffee extends BeverageTemplate {

    @Override
    protected void brew() {
        System.out.println("步骤2：将咖啡加入沸水中");

    }

    @Override
    protected void addCondiments() {
        System.out.println("步骤4：往杯中加入糖和牛奶");
    }

}
