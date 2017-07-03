package tdd.vendingMachine.exception;

public class NoSufficientMoney extends Exception {

    @Override
    public String getMessage() {
        return "Cannot withdraw - no sufficient money left.";
    }

}
