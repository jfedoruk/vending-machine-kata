package tdd.vendingMachine;

import tdd.vendingMachine.atm.Coin;
import tdd.vendingMachine.atm.VendingMachineATM;
import tdd.vendingMachine.products.Product;
import tdd.vendingMachine.products.ProductFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    public static final BigDecimal ZERO = BigDecimal.valueOf(0.0);

    private String separator = "######## Vending Machine ########";
    private int selectedShelve = 0;
    private List<Product> shelves;
    private VendingMachineATM atm = new VendingMachineATM();
    private BigDecimal currentBalance = ZERO;

    public void deposit(Coin coin) {
        atm.deposit(coin);
        currentBalance = currentBalance.add(coin.value());
    }

    public BigDecimal getMoney() {
        return atm.getMoney();
    }

    private int getSelectedShelveForDisplay() {
        if (shelves == null) {
            return 0;
        }
        return selectedShelve + 1;
    }

    public int getShelvesCount() {
        if (shelves == null) {
            return 0;
        }
        return shelves.size();
    }

    public void putProductsOnShelves() {
        ProductFactory productFactory = new ProductFactory();

        shelves = new ArrayList<>();
        shelves.add(productFactory.getProduct("COLA"));
        shelves.add(productFactory.getProduct("CHOCOLATE"));
        shelves.add(productFactory.getProduct("BEER"));
        shelves.add(productFactory.getProduct("WATER"));
    }

    public void selectShelve(int selectedShelve) throws Exception {
        if (selectedShelve < 0) {
            throw new Exception("Shelve number cannot be negative.");
        }
        this.selectedShelve = selectedShelve;
    }

    /**
     * Vending Machine display in ASCII.
     * <p>
     * Display shows:
     * <uL>
     * <li>money inside vending machine
     * <li>selected shelve and their count
     * </uL>
     */
    public void display() {
        System.out.println(separator);
        System.out.println("Money: " + getMoney());
        System.out.println("Selected Shelve: " + getSelectedShelveForDisplay() + " of " + getShelvesCount());
        if (shelves != null && shelves.get(selectedShelve) != null) {
            System.out.println("Product: " + shelves.get(selectedShelve).toString());
        }
        System.out.println("Current balance: " + currentBalance);
        if (currentBalance.compareTo(ZERO) == 1) {
            System.out.println("Remaining: " + shelves.get(selectedShelve).price().subtract(currentBalance));
        }
        if (shelves != null && currentBalance.compareTo(shelves.get(selectedShelve).price()) >= 0) {
            System.out.println("Successful purchase!");
            System.out.println("Disposing product: " + shelves.get(selectedShelve).toString());
        }
        System.out.println(separator);
    }

}
