package tdd.vendingMachine.products;

/**
 * Water implementation of Vending Machine Product.
 */
public class Water implements Product {

    private double price = 1.1;

    @Override
    public double price() {
        return price;
    }

    @Override
    public String toString() {
        return "Water {" +
            "price = " + price +
            '}';
    }
}
