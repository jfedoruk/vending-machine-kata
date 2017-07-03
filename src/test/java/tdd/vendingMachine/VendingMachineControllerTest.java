package tdd.vendingMachine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tdd.vendingMachine.controller.VendingMachineController;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class VendingMachineControllerTest {

    @Autowired
    VendingMachineController vendingMachineController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testVendingMachine() {
        assertThat(vendingMachineController, instanceOf(VendingMachineController.class));
    }

    @Test
    public void testHomePage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Vending Machine Home Page")));
    }

    @Test
    public void testListOfProducts() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(allOf(
                containsString("Beer"),
                containsString("Chocolate"),
                containsString("Cola"),
                containsString("Water"))));
    }

    @Test
    public void testMoney() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Money: 0")));
    }

    @Test
    public void testShelveSelection() throws Exception {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.selectShelve(1);
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
            .andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("<option selected=\"selected\" value=\"0\">Cola {price = 2.5}</option>")));

        this.mockMvc.perform(post("/selectShelve").param("selectedShelve", "2")).andDo(print());

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
            .andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("<option selected=\"selected\" value=\"2\">Beer {price = 4.3}</option>")));
    }

}
