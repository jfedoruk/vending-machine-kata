package tdd.vendingMachine.atm;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static tdd.vendingMachine.atm.Coin.*;

/**
 * ATM for VendingMachine.
 * Supports deposit and withdrawal of coins.
 */
public class VendingMachineATM {

    private BigDecimal money = BigDecimal.valueOf(0.0);
    private HashMap<Coin, Integer> coins = new HashMap<>();
    private Dispenser firstInChain;
    private Dispenser chainTwo;
    private Dispenser chainThree;
    private Dispenser chainFour;
    private Dispenser chainFive;
    private Dispenser chainSix;

    {
        firstInChain = new Dispenser(FIVE);
        chainTwo = new Dispenser(TWO);
        chainThree = new Dispenser(ONE);
        chainFour = new Dispenser(POINT_FIVE);
        chainFive = new Dispenser(POINT_TWO);
        chainSix = new Dispenser(POINT_ONE);

        firstInChain.setNextInChain(chainTwo);
        chainTwo.setNextInChain(chainThree);
        chainThree.setNextInChain(chainFour);
        chainFour.setNextInChain(chainFive);
        chainFive.setNextInChain(chainSix);
    }

    private void addCoin(Coin coin) {
        if (coins.containsKey(coin)) {
            coins.put(coin, coins.get(coin) + 1);
        } else {
            coins.put(coin, 1);
        }
    }

    public boolean getChange(BigDecimal change) {
        HashMap<Coin, Integer> usedCoins = firstInChain.dispense(change, coins, new HashMap<>());

        if (usedCoins.size() > 0) {
            Iterator it = usedCoins.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Integer amount = (Integer) pair.getValue();
                Coin coin = (Coin) pair.getKey();

                for (int i = 0; i < amount; i++) {
                    try {
                        withdraw(coin);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                it.remove();
            }
            return true;
        }
        return false;
    }

    public int getCoins(Coin coin) {
        if (coins.containsKey(coin)) {
            return coins.get(coin);
        }
        return 0;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void deposit(Coin coin) {
        money = money.add(coin.value());
        addCoin(coin);
    }

    private void removeCoin(Coin coin) throws Exception {
        if (coins.get(coin) >= 1) {
            coins.put(coin, coins.get(coin) - 1);
        } else {
            throw new Exception("Cannot withdraw - no coins of this type left.");
        }
    }

    public void withdraw(Coin coin) throws Exception {
        if (coin.value().compareTo(money) == 1) {
            throw new Exception("Cannot withdraw - no sufficient money left.");
        }
        money = money.subtract(coin.value());
        removeCoin(coin);
    }

}
