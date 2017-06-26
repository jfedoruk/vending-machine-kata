package tdd.vendingMachine.atm;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * ATM for VendingMachine.
 * Supports deposit and withdrawal of coins.
 */
public class VendingMachineATM {

    private BigDecimal money = BigDecimal.valueOf(0.0);
    private HashMap<Coin, Integer> coins = new HashMap<>();

    private void addCoin(Coin coin) {
        if (coins.containsKey(coin)) {
            coins.put(coin, coins.get(coin) + 1);
        } else {
            coins.put(coin, 1);
        }
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void deposit(Coin coin) {
        money = money.add(coin.value());
        addCoin(coin);
    }

    public void withdraw(Coin coin) throws Exception {
        if (coin.value().compareTo(money) == 1) {
            throw new Exception("Cannot withdraw - no sufficient money left.");
        }
        money = money.subtract(coin.value());
    }

    public int getCoins(Coin coinType) {
        if (coins.containsKey(coinType)) {
            return coins.get(coinType);
        }
        return 0;
    }
}
