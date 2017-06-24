package tdd.vendingMachine.products;

/**
 * Cola implementation of Vending Machine Product.
 */
public class Cola implements Product {

    private double price = 2.5;

    @Override
    public double price() {
        return price;
    }
}
