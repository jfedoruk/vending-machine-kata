package tdd.vendingMachine;

public class VendingMachine {

    private String separator = "######## Vending Machine ########";
    private double money = 0;
    private int selectedShelve = 0;

    public void selectShelve(int selectedShelve) {
        this.selectedShelve = selectedShelve;
    }

    public double getMoney() {
        return money;
    }

    /**
     * Vending Machine display in ASCII.
     *
     * Display shows:
     * - money inside vending machine
     * - selected shelve
     */
    public void display() {
        System.out.println(separator);
        System.out.println("Money: " + getMoney());
        System.out.println("Selected Shelve: " + selectedShelve);
        System.out.println(separator);
    }
}
