package tdd.vendingMachine;

import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.atm.VendingMachineATM;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class VendingMachineATMTest {

    private VendingMachineATM atm;

    @Before
    public void setUp() {
        atm = new VendingMachineATM();
    }

    @Test
    public void testVendingMachineATM() {
        assertEquals(BigDecimal.valueOf(0), atm.getMoney());
    }

    @Test
    public void testDeposit() {
        assertEquals(BigDecimal.valueOf(0), atm.getMoney());

        atm.deposit(5);
        assertEquals(BigDecimal.valueOf(5.0), atm.getMoney());

        atm.deposit(2);
        assertEquals(BigDecimal.valueOf(7.0), atm.getMoney());

        atm.deposit(1);
        assertEquals(BigDecimal.valueOf(8.0), atm.getMoney());

        atm.deposit(0.5);
        assertEquals(BigDecimal.valueOf(8.5), atm.getMoney());

        atm.deposit(0.2);
        assertEquals(BigDecimal.valueOf(8.7), atm.getMoney());

        atm.deposit(0.1);
        assertEquals(BigDecimal.valueOf(8.8), atm.getMoney());
    }

}
