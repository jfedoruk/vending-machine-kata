package tdd.vendingMachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tdd.vendingMachine.VendingMachine;

@Controller
public class VendingMachineController {

    @Autowired
    VendingMachine vendingMachine;

    @RequestMapping("/")
    String home() {
        return "vendingMachine";
    }

}
