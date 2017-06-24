package tdd.vendingMachine;

public class VendingMachine {

    private String separator = "######## Vending Machine ########";
    private double money = 0;

    public double getMoney() {
        return money;
    }

    public void display() {
        System.out.println(separator);
        System.out.println("Money: " + getMoney());
        System.out.println(separator);
    }
}
