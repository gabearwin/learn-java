package xyz.gabear.learn.designpattern.template;

public abstract class BeverageTemplate {

    /**
     * 准备饮料的通用模板
     */
    public final void prepare() {
        boilWater();
        brew();
        pourInCup();
        if (isCustomerWantsCondiments()) {
            addCondiments();
        }
    }

    // 公共步骤1
    private void boilWater() {
        System.out.println("步骤1：将水煮沸");
    }

    // 需要子类实现步骤2
    protected abstract void brew();

    // 公共步骤3
    private void pourInCup() {
        System.out.println("步骤3：降饮料倒入杯中");
    }

    // 需要子类实现步骤4
    protected abstract void addCondiments();

    // (HOOK 是否需要调味品) 钩子函数，父类提供默认实现，子类也可以决定是否挂钩或者如何挂钩。
    protected boolean isCustomerWantsCondiments() {
        return true;
    }

}
