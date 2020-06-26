package xyz.gabear.learn.designpattern.cor;

import org.junit.Test;
import xyz.gabear.learn.designpattern.cor.handler.PriceHandlerFactory;

import java.util.Random;

public class CustomerTest {

    @Test
    public void requestDiscount() {
        Customer customer = new Customer();
        customer.setPriceHandler(PriceHandlerFactory.createPriceHandler());

        Random rand = new Random();

        for (int i = 1; i <= 5; i++) {
            System.out.print(i + ":");
            customer.requestDiscount(rand.nextFloat());
        }
    }
}