package tdd.vendingMachine.exception;

public class NegativeShelveNumberException extends Exception {

    @Override
    public String getMessage() {
        return "Shelve number cannot be negative.";
    }

}
