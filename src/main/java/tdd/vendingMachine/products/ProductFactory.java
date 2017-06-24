package tdd.vendingMachine.products;

/**
 * Factory for Vending Machine products.
 */
public class ProductFactory {

    public Product getProduct(String productType) {
        if (productType == null) {
            return null;
        }

        if (productType.equalsIgnoreCase("COLA")) {
            return new Cola();
        } else if (productType.equalsIgnoreCase("CHOCOLATE")) {
            return new Chocolate();
        } else if (productType.equalsIgnoreCase("WATER")) {
            return new Water();
        } else if (productType.equalsIgnoreCase("BEER")) {
            return new Beer();
        }

        return null;
    }
}
