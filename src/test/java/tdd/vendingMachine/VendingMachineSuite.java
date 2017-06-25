package tdd.vendingMachine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.math.BigDecimal;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    VendingMachineTest.class,
    VendingMachineProductTest.class,
    VendingMachineATMTest.class
})
public class VendingMachineSuite {

    public static final BigDecimal ZERO = BigDecimal.valueOf(0.0);
}
