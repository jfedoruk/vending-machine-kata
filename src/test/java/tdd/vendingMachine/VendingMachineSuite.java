package tdd.vendingMachine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    VendingMachineTest.class,
    VendingMachineProductTest.class,
    VendingMachineATMTest.class
})
public class VendingMachineSuite {

}
