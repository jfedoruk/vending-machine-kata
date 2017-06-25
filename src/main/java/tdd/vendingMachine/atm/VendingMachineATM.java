package tdd.vendingMachine.atm;

import java.math.BigDecimal;

/**
 * ATM for VendingMachine.
 * Supports deposit and withdrawal of coins.
 */
public class VendingMachineATM {

    private BigDecimal money = BigDecimal.valueOf(0.0);

    public BigDecimal getMoney() {
        return money;
    }

    public void deposit(Coin coin) {
        money = money.add(coin.value());
    }

    public void withdraw(Coin coin) throws Exception {
        if (coin.value().compareTo(money) == 1) {
            throw new Exception("Cannot withdraw - no sufficient money left.");
        }

        money = money.subtract(coin.value());
    }
}
