package tdd.vendingMachine.atm;

import java.math.BigDecimal;

/**
 * ATM for VendingMachine.
 * Supports deposit and withdrawal of coins.
 */
public class VendingMachineATM {

    private BigDecimal money = new BigDecimal(0);

    public BigDecimal getMoney() {
        return money;
    }

    public void deposit(double coin) {
        money = money.add(BigDecimal.valueOf(coin));
    }

    public void withdraw(double coin) throws Exception {
        BigDecimal coinBD = BigDecimal.valueOf(coin);
        if (coinBD.compareTo(money) == 1) {
            throw new Exception("Cannot withdraw - no sufficient money left.");
        }

        money = money.subtract(coinBD);
    }
}
