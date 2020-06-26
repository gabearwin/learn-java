package xyz.gabear.learn.designpattern.template.impl;

import xyz.gabear.learn.designpattern.template.BeverageTemplate;

public class Tea extends BeverageTemplate {

    @Override
    protected void brew() {
        System.out.println("步骤2：将茶叶加入沸水中");
    }

    @Override
    protected void addCondiments() {
        System.out.println("步骤4：往杯中加入柠檬");
    }

    /**
     * 子类可以重写钩子函数以决定是否挂钩以及如何挂钩
     */
    @Override
    protected boolean isCustomerWantsCondiments() {
        return false;
    }

}
