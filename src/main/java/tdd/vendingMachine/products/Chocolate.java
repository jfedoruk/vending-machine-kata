package tdd.vendingMachine.products;

import java.math.BigDecimal;

/**
 * Chocolate implementation of Vending Machine Product.
 */
public class Chocolate implements Product {

    private BigDecimal price = BigDecimal.valueOf(3.5);

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public String toString() {
        return "Chocolate {" +
            "price = " + price +
            '}';
    }
}
