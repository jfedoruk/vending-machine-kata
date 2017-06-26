package tdd.vendingMachine.atm;

import java.math.BigDecimal;
import java.util.HashMap;

public interface DispenseChain {

    void setNextInChain(DispenseChain nextInChain);

    boolean dispense(BigDecimal change, HashMap<Coin, Integer> coins);
}
