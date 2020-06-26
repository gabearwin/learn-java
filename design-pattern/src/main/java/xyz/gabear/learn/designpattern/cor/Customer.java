package xyz.gabear.learn.designpattern.cor;

import xyz.gabear.learn.designpattern.cor.handler.PriceHandler;

/**
 * 客户，请求折扣。通过折扣审核链进行处理
 */
public class Customer {

    private PriceHandler priceHandler;

    public void setPriceHandler(PriceHandler priceHandler) {
        this.priceHandler = priceHandler;
    }

    public void requestDiscount(float discount) {
        priceHandler.processDiscount(discount);
    }

}
