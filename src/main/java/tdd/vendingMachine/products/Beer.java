package tdd.vendingMachine.products;

import java.math.BigDecimal;

/**
 * Beer implementation of Vending Machine Product.
 */
public class Beer implements Product {

    private final BigDecimal price = BigDecimal.valueOf(4.3);

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public String toString() {
        return "Beer {" +
            "price = " + price +
            '}';
    }
}
