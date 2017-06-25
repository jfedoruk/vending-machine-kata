package tdd.vendingMachine.atm;

import java.math.BigDecimal;

/**
 * Coin types for ATM.
 */
public enum Coin {
    FIVE(BigDecimal.valueOf(5.0)),
    TWO(BigDecimal.valueOf(2.0)),
    ONE(BigDecimal.valueOf(1.0)),
    POINT_FIVE(BigDecimal.valueOf(0.5)),
    POINT_TWO(BigDecimal.valueOf(0.2)),
    POINT_ONE(BigDecimal.valueOf(0.1));

    private final BigDecimal value;

    Coin(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal value() {
        return value;
    }
}
