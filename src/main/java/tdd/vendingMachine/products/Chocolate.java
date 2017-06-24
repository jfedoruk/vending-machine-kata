package tdd.vendingMachine.products;

/**
 * Chocolate implementation of Vending Machine Product.
 */
public class Chocolate implements Product {

    private double price = 3.5;

    @Override
    public double price() {
        return price;
    }
}
