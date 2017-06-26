package tdd.vendingMachine.atm;

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
        return chain.dispense(change, coins);
    }
}
