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
        }

        return null;
    }
}
