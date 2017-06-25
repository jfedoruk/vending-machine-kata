package tdd.vendingMachine;

import org.junit.Test;
import tdd.vendingMachine.atm.VendingMachineATM;

import static org.junit.Assert.assertEquals;

public class VendingMachineATMTest {

    @Test
    public void testVendingMachineATM() {
        VendingMachineATM atm = new VendingMachineATM();
        assertEquals(0, atm.getMoney(), 0.0);
    }

}
