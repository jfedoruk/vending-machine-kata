package tdd.vendingMachine.products;

import java.math.BigDecimal;

/**
 * Cola implementation of Vending Machine Product.
 */
public class Cola implements Product {

    private final BigDecimal price = BigDecimal.valueOf(2.5);

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public String toString() {
        return "Cola {" +
            "price = " + price +
            '}';
    }
}
