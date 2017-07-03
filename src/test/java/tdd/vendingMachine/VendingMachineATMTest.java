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
import tdd.vendingMachine.exception.NoSufficientCoins;
import tdd.vendingMachine.exception.NoSufficientMoney;

import java.math.BigDecimal;

import static java.util.function.Predicate.isEqual;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static tdd.vendingMachine.atm.Coin.*;

@RunWith(JUnitParamsRunner.class)
public class VendingMachineATMTest {

    private VendingMachineATM atm;

    private void depositAllTypesOfCoins() {
        for (Coin coin : Coin.values()) {
            atm.deposit(coin);
        }
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        atm = new VendingMachineATM();
    }

    @Test
    public void testVendingMachineATM() {
        assertEquals(BigDecimal.ZERO, atm.getMoney());
    }

    @Test
    public void testDeposit() {
        assertEquals(BigDecimal.ZERO, atm.getMoney());

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
        assertEquals(BigDecimal.ZERO, atm.getMoney());

        depositAllTypesOfCoins();

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
        exception.expect(NoSufficientMoney.class);
        exception.expectMessage("Cannot withdraw - no sufficient money left.");

        atm.withdraw(ONE);
    }

    @Test
    @Parameters(source = Coin.class)
    public void testNumberOfCoinsDeposit(Coin coin) {
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

    @Test
    public void testDifferentCoinsDeposit() {
        depositAllTypesOfCoins();

        assertEquals(BigDecimal.valueOf(8.8), atm.getMoney());

        for (Coin coin : Coin.values()) {
            assertEquals(1, atm.getCoins(coin));
        }

        atm.deposit(ONE);
        atm.deposit(POINT_ONE);
        atm.deposit(POINT_FIVE);
        atm.deposit(POINT_FIVE);
        assertEquals(BigDecimal.valueOf(10.9), atm.getMoney());
        assertEquals(2, atm.getCoins(ONE));
        assertEquals(2, atm.getCoins(POINT_ONE));
        assertEquals(3, atm.getCoins(POINT_FIVE));
    }

    @Test
    @Parameters(source = Coin.class)
    public void testNumberOfCoinsWithdrawal(Coin coin) throws Exception {
        for (Coin coinType : Coin.values()) {
            assertEquals(0, atm.getCoins(coinType));
        }
        depositAllTypesOfCoins();

        atm.withdraw(coin);

        for (Coin coinType : Coin.values()) {
            if (coinType == coin) {
                assertEquals(0, atm.getCoins(coinType));
            } else {
                assertEquals(1, atm.getCoins(coinType));
            }
        }
    }

    @Test
    @Parameters(source = Coin.class)
    public void testNumberOfCoinsWithdrawalNegative(Coin coin) throws Exception {
        depositAllTypesOfCoins();

        exception.expect(anyOf(instanceOf(NoSufficientCoins.class), instanceOf(NoSufficientMoney.class)));
        exception.expectMessage(anyOf(equalTo("Cannot withdraw - no sufficient money left."),
            equalTo("Cannot withdraw - no coins of this type left.")));

        atm.withdraw(coin);
        atm.withdraw(coin);
    }

    @Test
    @Parameters(source = Coin.class)
    public void testGetChangeOneCoin(Coin coin) {
        depositAllTypesOfCoins();

        assertTrue(atm.getChange(coin.value()));
    }

    @Test
    @Parameters({"FIVE", "TWO", "ONE", "POINT_FIVE"})
    public void testGetChangeOutMultipleDepositOne(Coin coin) {
        depositAllTypesOfCoins();

        assertTrue(atm.getChange(BigDecimal.valueOf(0.1).add(coin.value())));
        assertEquals(0, atm.getCoins(POINT_ONE));
        assertEquals(0, atm.getCoins(coin));
        atm.deposit(POINT_ONE);
        atm.deposit(coin);
        assertTrue(atm.getChange(BigDecimal.valueOf(0.2).add(coin.value())));
        assertEquals(0, atm.getCoins(POINT_TWO));
        assertEquals(0, atm.getCoins(coin));
        atm.deposit(POINT_TWO);
        atm.deposit(coin);
        assertTrue(atm.getChange(BigDecimal.valueOf(0.3).add(coin.value())));
        assertEquals(0, atm.getCoins(POINT_ONE));
        assertEquals(0, atm.getCoins(POINT_TWO));
        assertEquals(0, atm.getCoins(coin));
        atm.deposit(POINT_ONE);
        atm.deposit(POINT_TWO);
        atm.deposit(coin);
        assertTrue(atm.getChange(BigDecimal.valueOf(0.5).add(coin.value())));
        if (coin != POINT_FIVE) {
            assertEquals(0, atm.getCoins(POINT_FIVE));
            assertEquals(0, atm.getCoins(coin));
            atm.deposit(POINT_FIVE);
            atm.deposit(coin);
        } else {
            assertEquals(0, atm.getCoins(ONE));
            assertEquals(1, atm.getCoins(coin));
            atm.deposit(ONE);
        }
        assertTrue(atm.getChange(BigDecimal.valueOf(0.6).add(coin.value())));
        if (coin != POINT_FIVE) {
            assertEquals(0, atm.getCoins(POINT_ONE));
            assertEquals(0, atm.getCoins(POINT_FIVE));
            assertEquals(0, atm.getCoins(coin));
            atm.deposit(POINT_ONE);
            atm.deposit(POINT_FIVE);
            atm.deposit(coin);
        } else {
            assertEquals(0, atm.getCoins(POINT_ONE));
            assertEquals(0, atm.getCoins(ONE));
            assertEquals(1, atm.getCoins(coin));
            atm.deposit(POINT_ONE);
            atm.deposit(ONE);
        }
        assertTrue(atm.getChange(BigDecimal.valueOf(0.7).add(coin.value())));
        if (coin != POINT_FIVE) {
            assertEquals(0, atm.getCoins(POINT_TWO));
            assertEquals(0, atm.getCoins(POINT_FIVE));
            assertEquals(0, atm.getCoins(coin));
            atm.deposit(POINT_TWO);
            atm.deposit(POINT_FIVE);
            atm.deposit(coin);
        } else {
            assertEquals(0, atm.getCoins(POINT_TWO));
            assertEquals(0, atm.getCoins(ONE));
            assertEquals(1, atm.getCoins(coin));
            atm.deposit(POINT_TWO);
            atm.deposit(ONE);
        }
        assertTrue(atm.getChange(BigDecimal.valueOf(0.8).add(coin.value())));
        if (coin != POINT_FIVE) {
            assertEquals(0, atm.getCoins(POINT_ONE));
            assertEquals(0, atm.getCoins(POINT_FIVE));
            assertEquals(0, atm.getCoins(coin));
            atm.deposit(POINT_ONE);
            atm.deposit(POINT_FIVE);
            atm.deposit(coin);
        } else {
            assertEquals(0, atm.getCoins(POINT_ONE));
            assertEquals(0, atm.getCoins(POINT_TWO));
            assertEquals(0, atm.getCoins(ONE));
            assertEquals(1, atm.getCoins(coin));
            atm.deposit(POINT_ONE);
            atm.deposit(POINT_TWO);
            atm.deposit(ONE);
        }
        assertTrue(atm.getChange(BigDecimal.valueOf(1.0).add(coin.value())));
        if (coin != ONE) {
            assertEquals(0, atm.getCoins(ONE));
            assertEquals(0, atm.getCoins(coin));
            atm.deposit(ONE);
            atm.deposit(coin);
        } else {
            assertEquals(0, atm.getCoins(TWO));
            assertEquals(1, atm.getCoins(coin));
            atm.deposit(TWO);
        }

        assertFalse(atm.getChange(BigDecimal.valueOf(0.4).add(coin.value())));
        assertFalse(atm.getChange(BigDecimal.valueOf(0.9).add(coin.value())));
    }

    @Test
    public void testChangeWithDifferentDenominations() {
        for (int i = 0; i < 10; i++) {
            atm.deposit(ONE);
        }
        atm.deposit(POINT_FIVE);
        atm.deposit(POINT_FIVE);

        assertEquals(10, atm.getCoins(ONE));
        assertEquals(2, atm.getCoins(POINT_FIVE));
        assertEquals(BigDecimal.valueOf(11.0), atm.getMoney());
        assertTrue(atm.getChange(atm.getMoney()));
        assertEquals(BigDecimal.ZERO.setScale(1, BigDecimal.ROUND_HALF_UP), atm.getMoney());
        assertEquals(0, atm.getCoins(ONE));
        assertEquals(0, atm.getCoins(POINT_FIVE));


        for (int i = 0; i < 10; i++) {
            atm.deposit(ONE);
        }
        atm.deposit(POINT_FIVE);
        atm.deposit(POINT_FIVE);

        assertEquals(10, atm.getCoins(ONE));
        assertEquals(2, atm.getCoins(POINT_FIVE));
        assertEquals(BigDecimal.valueOf(11.0), atm.getMoney());
        assertTrue(atm.getChange(BigDecimal.valueOf(5.0)));
        assertEquals(BigDecimal.valueOf(6.0), atm.getMoney());
        assertEquals(5, atm.getCoins(ONE));
        assertEquals(2, atm.getCoins(POINT_FIVE));

        assertTrue(atm.getChange(BigDecimal.valueOf(5.5)));
        assertEquals(BigDecimal.valueOf(0.5), atm.getMoney());
        assertEquals(0, atm.getCoins(ONE));
        assertEquals(1, atm.getCoins(POINT_FIVE));
    }
}
