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
}
