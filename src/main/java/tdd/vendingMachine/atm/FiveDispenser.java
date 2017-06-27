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
    public HashMap<Coin, Integer> dispense(BigDecimal change, HashMap<Coin, Integer> coins, HashMap<Coin, Integer> usedCoins) {
        if (coins.containsKey(coin)) {
            Integer coinsAvailable = coins.get(coin);
            BigDecimal amountAvailable = coin.value().multiply(BigDecimal.valueOf(coinsAvailable));

            /* Proceed only when we can dispense at least 1 coin of this type */
            if (change.compareTo(coin.value()) >= 0) {
                BigDecimal quotientAndRemainder[] = change.divideAndRemainder(coin.value());

                /*
                 * Check if we can dispense using only this type of coins
                 * and we have enough coins to do that.
                 */
                if ((quotientAndRemainder[1].compareTo(VendingMachine.ZERO) == 0) &&
                    (change.compareTo(amountAvailable) <= 0)) {
                    usedCoins.put(coin, coinsAvailable);
                    return usedCoins;
                }

                BigDecimal changeDispensed = coin.value().multiply(quotientAndRemainder[0]);
                usedCoins.put(coin, quotientAndRemainder[0].intValue());
                return chain.dispense(change.subtract(changeDispensed), coins, usedCoins);
            }
        }
        return chain.dispense(change, coins, usedCoins);
    }
}
