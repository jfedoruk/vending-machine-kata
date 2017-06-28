package tdd.vendingMachine;

import tdd.vendingMachine.products.Product;

import java.math.BigDecimal;
import java.util.List;

public final class VendingMachineDisplay {

    private final static String separator = "######## Vending Machine ########";

    /**
     * Vending Machine display status in ASCII.
     * <p>
     * Display shows:
     * <uL>
     * <li>money inside vending machine
     * <li>selected shelve and number of shelves (if there are shelves)
     * <li>selected product
     * <li>current balance
     * </uL>
     */
    public static void displayStatus(BigDecimal money,
                                     int selectedShelve,
                                     List<Product> shelves,
                                     BigDecimal currentBalance) {
        Product product = null;

        System.out.println(separator);
        System.out.println("Money: " + money.setScale(2));
        if (shelves != null) {
            product = shelves.get(selectedShelve);
            System.out.println("Selected Shelve: " + getSelectedShelveForDisplay(selectedShelve) +
                " of " + shelves.size());

            System.out.println("Product: " + product.toString());

            System.out.println("Current balance: " + currentBalance);
            if (currentBalance.compareTo(BigDecimal.ZERO) == 1) {
                BigDecimal remaining = product.price().subtract(currentBalance);
                System.out.println("Remaining: " + remaining);
            }
        } else {
            System.out.println("No shelves - sorry we're empty:(");
        }

        System.out.println(separator);
    }

    private static int getSelectedShelveForDisplay(int selectedShelve) {
        return selectedShelve + 1;
    }

    public static void displayCancelMessage(BigDecimal currentBalance) {
        System.out.println(separator);
        System.out.println("Purchase canceled! Not enough money to give out the change :(");
        System.out.println("Change: " + currentBalance);
        System.out.println(separator);
    }

    public static void displayPurchaseMessage(Product product, BigDecimal change) {
        System.out.println(separator);
        System.out.println("Successful purchase!");
        System.out.println("Disposing product: " + product.toString());
        System.out.println("Change: " + change);
        System.out.println(separator);
    }

}
