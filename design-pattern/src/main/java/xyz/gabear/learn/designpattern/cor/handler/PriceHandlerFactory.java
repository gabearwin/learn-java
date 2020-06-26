package xyz.gabear.learn.designpattern.cor.handler;

public class PriceHandlerFactory {

    /**
     * 创建PriceHandler的工厂方法
     */
    public static PriceHandler createPriceHandler() {

        PriceHandler sales = new Sales();
        PriceHandler manager = new Manager();
        PriceHandler leader = new Leader();

        sales.setSuccessor(manager);
        manager.setSuccessor(leader);

        return sales;
    }

}
