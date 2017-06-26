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

    private int selectedShelve = 0;
    private List<Product> shelves;
    private VendingMachineATM atm = new VendingMachineATM();
    private BigDecimal currentBalance = ZERO;

    public void deposit(Coin coin) {
        atm.deposit(coin);
        currentBalance = currentBalance.add(coin.value());
    }

    public void display() {
        VendingMachineDisplay.display(getMoney(), selectedShelve, shelves, currentBalance);
    }


    /**
     * Checks if ATM can give out the change and if true withdraw it.
     *
     * @param change - amount of change
     * @return true if change is possible, false otherwise
     */
    public boolean getChange(BigDecimal change) {
        return atm.getChange(change);
    }

    public BigDecimal getMoney() {
        return atm.getMoney();
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


}
