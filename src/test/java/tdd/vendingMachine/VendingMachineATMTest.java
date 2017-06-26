package tdd.vendingMachine;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import tdd.vendingMachine.atm.Coin;
import tdd.vendingMachine.atm.VendingMachineATM;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static tdd.vendingMachine.VendingMachine.ZERO;
import static tdd.vendingMachine.atm.Coin.*;

@RunWith(JUnitParamsRunner.class)
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
        assertEquals(BigDecimal.valueOf(0.0), atm.getMoney());
    }

    @Test
    public void testDeposit() {
        assertEquals(ZERO, atm.getMoney());

        atm.deposit(FIVE);
        assertEquals(BigDecimal.valueOf(5.0), atm.getMoney());

        atm.deposit(TWO);
        assertEquals(BigDecimal.valueOf(7.0), atm.getMoney());

        atm.deposit(ONE);
        assertEquals(BigDecimal.valueOf(8.0), atm.getMoney());

        atm.deposit(POINT_FIVE);
        assertEquals(BigDecimal.valueOf(8.5), atm.getMoney());

        atm.deposit(POINT_TWO);
        assertEquals(BigDecimal.valueOf(8.7), atm.getMoney());

        atm.deposit(POINT_ONE);
        assertEquals(BigDecimal.valueOf(8.8), atm.getMoney());
    }

    @Test
    public void testWithdrawal() throws Exception {
        assertEquals(ZERO, atm.getMoney());

        atm.deposit(FIVE);
        atm.deposit(TWO);
        atm.deposit(ONE);
        atm.deposit(POINT_FIVE);
        atm.deposit(POINT_TWO);
        atm.deposit(POINT_ONE);
        assertEquals(BigDecimal.valueOf(8.8), atm.getMoney());

        atm.withdraw(FIVE);
        assertEquals(BigDecimal.valueOf(3.8), atm.getMoney());

        atm.withdraw(TWO);
        assertEquals(BigDecimal.valueOf(1.8), atm.getMoney());

        atm.withdraw(ONE);
        assertEquals(BigDecimal.valueOf(0.8), atm.getMoney());

        atm.withdraw(POINT_FIVE);
        assertEquals(BigDecimal.valueOf(0.3), atm.getMoney());

        atm.withdraw(POINT_TWO);
        assertEquals(BigDecimal.valueOf(0.1), atm.getMoney());

        atm.withdraw(POINT_ONE);
        assertEquals(BigDecimal.valueOf(0.0), atm.getMoney());
    }

    @Test
    public void testWithdrawalNegative() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("Cannot withdraw - no sufficient money left.");

        atm.withdraw(ONE);
    }

    @Test
    @Parameters({"FIVE", "TWO", "ONE", "POINT_FIVE", "POINT_TWO", "POINT_ONE"})
    public void testNumberOfCoins(Coin coin) {
        for (Coin coinType : Coin.values()) {
            assertEquals(0, atm.getCoins(coinType));
        }
        atm.deposit(coin);

        for (Coin coinType : Coin.values()) {
            if (coinType == coin) {
                assertEquals(1, atm.getCoins(coinType));
            } else {
                assertEquals(0, atm.getCoins(coinType));
            }
        }
    }

}
