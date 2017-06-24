package tdd.vendingMachine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testVendingMachine() {
        VendingMachine vendingMachine = new VendingMachine();

        assertEquals(vendingMachine.getMoney(), 0, 0.0);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Money: 0.0");
        assertThat(outContent.toString()).contains("Shelve: 0");
    }


    @Test
    public void testSelectingShelve() {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 0");

        vendingMachine.putProductsOnShelves();

        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 0");

        vendingMachine.selectShelve(1);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 1");

        vendingMachine.selectShelve(3);
        vendingMachine.display();
        assertThat(outContent.toString()).contains("Shelve: 3");
    }

}
