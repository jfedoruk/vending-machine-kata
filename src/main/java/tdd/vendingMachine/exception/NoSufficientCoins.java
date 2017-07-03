package tdd.vendingMachine.exception;

public class NoSufficientCoins extends Exception {

    @Override
    public String getMessage() {
        return "Cannot withdraw - no coins of this type left.";
    }

}
