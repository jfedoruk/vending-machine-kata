package tdd.vendingMachine;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static tdd.vendingMachine.atm.Coin.*;

public class VendingMachineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private VendingMachine vendingMachine;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        vendingMachine = new VendingMachine();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testVendingMachine() {
        assertEquals(BigDecimal.ZERO, vendingMachine.getMoney());
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Money: 0.0");
        assertThat(outContent.toString()).contains("No shelves - sorry we're empty:(");
    }

    @Test
    public void testSelectingShelve() throws Exception {
        vendingMachine.display();
        assertThat(outContent.toString()).contains("No shelves - sorry we're empty:(");
        outContent.reset();

        vendingMachine.putProductsOnShelves();

        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 1");
        outContent.reset();

        vendingMachine.selectShelve(1);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 2");
        outContent.reset();


        vendingMachine.selectShelve(2);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 3");
        outContent.reset();

        vendingMachine.selectShelve(3);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 4");
        outContent.reset();
    }

    @Test()
    public void testSelectingShelveNegative() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("Shelve number cannot be negative.");
        vendingMachine.putProductsOnShelves();
        vendingMachine.selectShelve(-1);
    }

    @Test()
    public void testShelvesCount() {
        assertEquals(0, vendingMachine.getShelvesCount());
        vendingMachine.display();
        assertThat(outContent.toString()).contains("No shelves - sorry we're empty:(");
        outContent.reset();

        vendingMachine.putProductsOnShelves();
        assertEquals(4, vendingMachine.getShelvesCount());
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 1 of 4");
    }

    @Test
    public void testProductInformation() throws Exception {
        vendingMachine.putProductsOnShelves();
        vendingMachine.selectShelve(0);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Product: Cola {price = 2.5}");
        outContent.reset();

        vendingMachine.selectShelve(1);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Product: Chocolate {price = 3.5}");
        outContent.reset();

        vendingMachine.selectShelve(2);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Product: Beer {price = 4.3}");
        outContent.reset();

        vendingMachine.selectShelve(3);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Product: Water {price = 1.1}");
        outContent.reset();
    }

    @Test
    public void testBuyWithoutChange() throws Exception {
        vendingMachine.putProductsOnShelves();
        vendingMachine.selectShelve(0);

        vendingMachine.deposit(TWO);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Current balance: 2.0");
        assertThat(outContent.toString()).contains("Remaining: 0.5");
        outContent.reset();

        vendingMachine.deposit(POINT_FIVE);
        assertThat(outContent.toString()).contains("Successful purchase!");
        assertThat(outContent.toString()).contains("Disposing product: Cola");
        outContent.reset();

        assertEquals(BigDecimal.valueOf(2.5), vendingMachine.getMoney());
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Money: 2.5");
    }

    @Test
    public void testBuyWithChange() throws Exception {
        vendingMachine.putProductsOnShelves();
        vendingMachine.selectShelve(1);

        vendingMachine.deposit(ONE);
        vendingMachine.deposit(ONE);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Product: Chocolate {price = 3.5}");
        assertThat(outContent.toString()).contains("Current balance: 2.0");
        assertThat(outContent.toString()).contains("Remaining: 1.5");
        outContent.reset();

        vendingMachine.deposit(POINT_FIVE);
        vendingMachine.deposit(TWO);
        assertThat(outContent.toString()).contains("Successful purchase!");
        assertThat(outContent.toString()).contains("Disposing product: Chocolate {price = 3.5}");
        assertThat(outContent.toString()).contains("Change: 1.0");
        outContent.reset();

        assertEquals(BigDecimal.valueOf(3.5), vendingMachine.getMoney());
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Money: 3.5");
    }

    @Test
    public void testBuyWithChangeTwo() throws Exception {
        vendingMachine.putProductsOnShelves();
        vendingMachine.selectShelve(1);

        vendingMachine.deposit(ONE);
        vendingMachine.deposit(ONE);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Product: Chocolate {price = 3.5}");
        assertThat(outContent.toString()).contains("Current balance: 2.0");
        assertThat(outContent.toString()).contains("Remaining: 1.5");
        outContent.reset();

        vendingMachine.deposit(POINT_FIVE);
        vendingMachine.deposit(TWO);
        assertThat(outContent.toString()).contains("Successful purchase!");
        assertThat(outContent.toString()).contains("Disposing product: Chocolate {price = 3.5}");
        assertThat(outContent.toString()).contains("Change: 1.0");
        outContent.reset();

        assertEquals(BigDecimal.valueOf(3.5), vendingMachine.getMoney());
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Money: 3.5");
        outContent.reset();

        vendingMachine.selectShelve(3);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Product: Water {price = 1.1}");
        assertThat(outContent.toString()).contains("Current balance: 0");
        outContent.reset();

        vendingMachine.deposit(POINT_TWO);
        vendingMachine.deposit(POINT_TWO);
        vendingMachine.deposit(POINT_ONE);
        vendingMachine.deposit(POINT_ONE);
        vendingMachine.deposit(ONE);
        assertThat(outContent.toString()).contains("Successful purchase!");
        assertThat(outContent.toString()).contains("Disposing product: Water {price = 1.1}");
        assertThat(outContent.toString()).contains("Change: 0.5");

        assertEquals(BigDecimal.valueOf(4.6), vendingMachine.getMoney());
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Money: 4.6");
        outContent.reset();
    }

    @Test
    public void testBuyWithChangeNegative() throws Exception {
        vendingMachine.putProductsOnShelves();
        vendingMachine.selectShelve(2);

        vendingMachine.deposit(TWO);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Product: Beer {price = 4.3}");
        assertThat(outContent.toString()).contains("Current balance: 2.0");
        assertThat(outContent.toString()).contains("Remaining: 2.3");
        outContent.reset();

        vendingMachine.deposit(FIVE);
        assertThat(outContent.toString()).contains("Purchase canceled! Not enough money to give out the change :(");
        assertThat(outContent.toString()).contains("Change: 7.0");
        outContent.reset();

        assertEquals(BigDecimal.valueOf(0.0), vendingMachine.getMoney());
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Money: 0.0");
    }

    @Test
    public void testGetChange() {
        vendingMachine.deposit(ONE);
        vendingMachine.deposit(FIVE);
        vendingMachine.deposit(POINT_FIVE);
        assertEquals(BigDecimal.valueOf(6.5), vendingMachine.getMoney());

        assertTrue(vendingMachine.getChange(BigDecimal.valueOf(1)));
        assertTrue(vendingMachine.getChange(BigDecimal.valueOf(0.5)));
        assertFalse(vendingMachine.getChange(BigDecimal.valueOf(2)));
    }

}
