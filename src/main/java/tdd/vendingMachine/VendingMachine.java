package tdd.vendingMachine;

import tdd.vendingMachine.products.Product;
import tdd.vendingMachine.products.ProductFactory;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private String separator = "######## Vending Machine ########";
    private double money = 0;
    private int selectedShelve = 0;
    private List<Product> shelves;

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

    public double getMoney() {
        return money;
    }

    public int getShelvesCount() {
        if (shelves == null) {
            return 0;
        }
        return shelves.size();
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
        System.out.println("Selected Shelve: " + selectedShelve + " of " + getShelvesCount());
        System.out.println(separator);
    }
}
