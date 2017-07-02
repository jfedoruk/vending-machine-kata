package tdd.vendingMachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import tdd.vendingMachine.VendingMachine;
import tdd.vendingMachine.products.Product;

import java.util.List;

@Controller
public class VendingMachineController {

    @Autowired
    VendingMachine vendingMachine;

    @RequestMapping("/")
    String home(ModelMap model) {
        List<Product> shelves = vendingMachine.getShelves();
        model.addAttribute("shelves", shelves);
        return "vendingMachine";
    }

}
