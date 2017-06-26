package tdd.vendingMachine.atm;

import tdd.vendingMachine.VendingMachine;

import java.math.BigDecimal;
import java.util.HashMap;

public class FiveDispenser implements DispenseChain {

    private Coin coin = Coin.FIVE;

    private DispenseChain chain;

    @Override
    public void setNextInChain(DispenseChain nextInChain) {
        this.chain = nextInChain;
    }

    @Override
    public boolean dispense(BigDecimal change, HashMap<Coin, Integer> coins) {
        if (coins.containsKey(coin)) {
            BigDecimal remainder = change.remainder(coin.value());

            if (remainder.compareTo(VendingMachine.ZERO) == 0) {
                int amountRequired = change.divide(coin.value()).intValue();
                int amountAvailable = coins.get(coin);

                if (amountAvailable >= amountRequired) {
                    BigDecimal changeDispensed = coin.value().multiply(BigDecimal.valueOf(amountRequired));
                    if (changeDispensed.equals(change)) return true;

                    return chain.dispense(change.subtract(changeDispensed), coins);
                }
            }
        }
        return chain.dispense(change, coins);
    }
}
