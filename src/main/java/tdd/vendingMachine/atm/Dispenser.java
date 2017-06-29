package tdd.vendingMachine.atm;

import java.math.BigDecimal;
import java.util.HashMap;

public class Dispenser {

    private Coin coin;

    private Dispenser chain;

    public Dispenser(Coin coin) {
        this.coin = coin;
    }

    public void setNextInChain(Dispenser nextInChain) {
        this.chain = nextInChain;
    }

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
                if ((quotientAndRemainder[1].compareTo(BigDecimal.ZERO) == 0) &&
                    (change.compareTo(amountAvailable) <= 0)) {
                    usedCoins.put(coin, quotientAndRemainder[0].intValue());
                    return usedCoins;
                }

                if (chain != null) {
                    /*
                     * Check how much coins we need to use.
                     */
                    BigDecimal coinsToUse;
                    if (amountAvailable.compareTo(change) > 0) {
                        coinsToUse = quotientAndRemainder[0];
                    } else {
                        coinsToUse = BigDecimal.valueOf(coinsAvailable);
                    }
                    BigDecimal changeDispensed = coin.value().multiply(coinsToUse);
                    usedCoins.put(coin, coinsToUse.intValue());
                    return chain.dispense(change.subtract(changeDispensed), coins, usedCoins);
                }
            }
        }
        if (chain != null) {
            return chain.dispense(change, coins, usedCoins);
        } else {
            return new HashMap<>();
        }
    }
}
