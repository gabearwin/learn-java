package xyz.gabear.learn.designpattern.proxy.cglibproxy;

/**
 * jdk动态代理的类都需要是实现接口的才可以，但是cglib可以直接代理类，不需要实现不实现接口什么的
 * 具体的原理是，cglib直接生成要代理类的子类，父类在执行方法的时候在子类中进行拦截。
 * 所以cglib要求要代理的类不能是final修饰的，不然的话继承不了
 */
public class Train {
    public void move() {
        System.out.println("火车在行驶中...");
    }
}
