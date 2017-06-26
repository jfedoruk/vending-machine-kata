package tdd.vendingMachine;

import tdd.vendingMachine.products.Product;

import java.math.BigDecimal;
import java.util.List;

import static tdd.vendingMachine.VendingMachine.ZERO;

public class VendingMachineDisplay {

    private final static String separator = "######## Vending Machine ########";

    /**
     * Vending Machine display in ASCII.
     * <p>
     * Display shows:
     * <uL>
     * <li>money inside vending machine
     * <li>selected shelve and number of shelves (if there are shelves)
     * <li>selected product
     * <li>current balance
     * </uL>
     */
    public static void display(BigDecimal money,
                               int selectedShelve,
                               List<Product> shelves,
                               BigDecimal currentBalance) {
        Product product = null;

        System.out.println(separator);
        System.out.println("Money: " + money);
        if (shelves != null) {
            product = shelves.get(selectedShelve);
            System.out.println("Selected Shelve: " + getSelectedShelveForDisplay(selectedShelve) +
                " of " + shelves.size());

            System.out.println("Product: " + product.toString());

            System.out.println("Current balance: " + currentBalance);
            if (currentBalance.compareTo(ZERO) == 1) {
                System.out.println("Remaining: " + product.price().subtract(currentBalance));
            }
            if (currentBalance.compareTo(product.price()) >= 0) {
                System.out.println("Successful purchase!");
                System.out.println("Disposing product: " + product.toString());
            }
        } else {
            System.out.println("No shelves - sorry we're empty:(");
        }

        System.out.println(separator);
    }

    private static int getSelectedShelveForDisplay(int selectedShelve) {
        return selectedShelve + 1;
    }
}
