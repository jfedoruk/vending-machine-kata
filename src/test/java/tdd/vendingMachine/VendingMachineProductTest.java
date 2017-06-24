package tdd.vendingMachine;

import org.junit.Test;
import tdd.vendingMachine.products.*;

import static org.junit.Assert.assertTrue;

public class VendingMachineProductTest {

    @Test
    public void testVendingMachineProduct() {
        ProductFactory productFactory = new ProductFactory();
        Product product = productFactory.getProduct("Cola");
        assertTrue(product instanceof Cola);
    }

    @Test
    public void testProductTypes() {
        ProductFactory productFactory = new ProductFactory();
        Product cola = productFactory.getProduct("Cola");
        Product chocolate = productFactory.getProduct("CHOCOLATE");
        Product water = productFactory.getProduct("WATER");
        Product beer = productFactory.getProduct("beer");

        assertTrue(cola instanceof Cola);
        assertTrue(chocolate instanceof Chocolate);
        assertTrue(water instanceof Water);
        assertTrue(beer instanceof Beer);
    }

}
