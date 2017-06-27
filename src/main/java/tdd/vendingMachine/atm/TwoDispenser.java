package tdd.vendingMachine.atm;

import tdd.vendingMachine.VendingMachine;

import java.math.BigDecimal;
import java.util.HashMap;

public class TwoDispenser implements DispenseChain {

    private Coin coin = Coin.TWO;

    private DispenseChain chain;

    @Override
    public void setNextInChain(DispenseChain nextInChain) {
        this.chain = nextInChain;
    }

    @Override
    public boolean dispense(BigDecimal change, HashMap<Coin, Integer> coins) {
        if (coins.containsKey(coin)) {
            BigDecimal coinsAvailable = BigDecimal.valueOf(coins.get(coin));
            BigDecimal amountAvailable = coin.value().multiply(coinsAvailable);

            /* Proceed only when we can dispense at least 1 coin of this type */
            if (change.compareTo(coin.value()) >= 0) {
                BigDecimal quotientAndRemainder[] = change.divideAndRemainder(coin.value());

                /*
                 * Check if we can dispense using only this type of coins
                 * and we have enough coins to do that.
                 */
                if ((quotientAndRemainder[1].compareTo(VendingMachine.ZERO) == 0) &&
                    (change.compareTo(amountAvailable) <= 0)) {
                    return true;
                }

                BigDecimal changeDispensed = coin.value().multiply(quotientAndRemainder[0]);
                return chain.dispense(change.subtract(changeDispensed), coins);
            }
        }
        return chain.dispense(change, coins);
    }
}
