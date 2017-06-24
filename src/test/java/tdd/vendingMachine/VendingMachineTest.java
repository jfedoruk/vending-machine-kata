package tdd.vendingMachine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    @Test
    public void testVendingMachine() {
        VendingMachine vendingMachine = new VendingMachine();

        assertEquals(vendingMachine.getMoney(), 0, 0.0);
    }

}
