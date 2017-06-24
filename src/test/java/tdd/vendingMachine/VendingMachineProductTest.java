package tdd.vendingMachine;

import org.junit.Test;
import tdd.vendingMachine.products.Cola;
import tdd.vendingMachine.products.Product;
import tdd.vendingMachine.products.ProductFactory;

import static org.junit.Assert.assertTrue;

public class VendingMachineProductTest {

    @Test
    public void testVendingMachineProduct() {
        ProductFactory productFactory = new ProductFactory();
        Product product = productFactory.getProduct("Cola");
        assertTrue(product instanceof Cola);
    }

}
