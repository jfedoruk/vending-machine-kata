package tdd.vendingMachine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tdd.vendingMachine.atm.VendingMachineATM;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class VendingMachineATMTest {

    private VendingMachineATM atm;

    @Rule
    public ExpectedException exception = ExpectedException.none();

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

    @Test
    public void testWithdrawal() throws Exception {
        assertEquals(BigDecimal.valueOf(0), atm.getMoney());

        atm.deposit(5);
        atm.deposit(2);
        atm.deposit(1);
        atm.deposit(0.5);
        atm.deposit(0.2);
        atm.deposit(0.1);
        assertEquals(BigDecimal.valueOf(8.8), atm.getMoney());

        atm.withdraw(5);
        assertEquals(BigDecimal.valueOf(3.8), atm.getMoney());

        atm.withdraw(2);
        assertEquals(BigDecimal.valueOf(1.8), atm.getMoney());

        atm.withdraw(1);
        assertEquals(BigDecimal.valueOf(0.8), atm.getMoney());

        atm.withdraw(0.5);
        assertEquals(BigDecimal.valueOf(0.3), atm.getMoney());

        atm.withdraw(0.2);
        assertEquals(BigDecimal.valueOf(0.1), atm.getMoney());

        atm.withdraw(0.1);
        assertEquals(BigDecimal.valueOf(0.0), atm.getMoney());
    }

    @Test
    public void testWithdrawalNegative() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("Cannot withdraw - no sufficient money left.");

        atm.withdraw(1);
    }
}
