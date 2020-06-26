package xyz.gabear.learn.designpattern.template;

import org.junit.Test;
import xyz.gabear.learn.designpattern.template.impl.Coffee;
import xyz.gabear.learn.designpattern.template.impl.Tea;

public class BeverageTemplateTest {
    @Test
    public void doPrepare() {
        BeverageTemplate template = new Coffee();
        template.prepare();

        System.out.println("\n***************\n");

        template = new Tea();
        template.prepare();
    }
}