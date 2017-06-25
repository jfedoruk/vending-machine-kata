package tdd.vendingMachine;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private VendingMachine vendingMachine;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        vendingMachine = new VendingMachine();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testVendingMachine() {
        assertEquals(0, vendingMachine.getMoney(), 0.0);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Money: 0.0");
        assertThat(outContent.toString()).contains("Shelve: 0");
    }

    @Test
    public void testSelectingShelve() throws Exception {
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 0");
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
        assertThat(outContent.toString()).contains("Shelve: 0 of 0");
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
}
