package tdd.vendingMachine.atm;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ATM for VendingMachine.
 * Supports deposit and withdrawal of coins.
 */
public class VendingMachineATM {

    private BigDecimal money = BigDecimal.valueOf(0.0);
    private HashMap<Coin, Integer> coins = new HashMap<>();
    private DispenseChain firstInChain;
    private DispenseChain chainTwo;
    private DispenseChain chainThree;
    private DispenseChain chainFour;
    private DispenseChain chainFive;
    private DispenseChain chainSix;

    {
        firstInChain = new FiveDispenser();
        chainTwo = new TwoDispenser();
        chainThree = new OneDispenser();
        chainFour = new PointFiveDispenser();
        chainFive = new PointTwoDispenser();
        chainSix = new PointOneDispenser();

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
