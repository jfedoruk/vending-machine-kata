package tdd.vendingMachine.products;

import java.math.BigDecimal;

/**
 * Water implementation of Vending Machine Product.
 */
public class Water implements Product {

    private BigDecimal price = BigDecimal.valueOf(1.1);

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public String toString() {
        return "Water {" +
            "price = " + price +
            '}';
    }
}
