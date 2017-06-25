package tdd.vendingMachine.products;

/**
 * Beer implementation of Vending Machine Product.
 */
public class Beer implements Product {

    private double price = 4.3;

    @Override
    public double price() {
        return price;
    }

    @Override
    public String toString() {
        return "Beer {" +
            "price = " + price +
            '}';
    }
}
