package tdd.vendingMachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tdd.vendingMachine.VendingMachine;

@Controller
public class VendingMachineController {

    @Autowired
    VendingMachine vendingMachine;

    @RequestMapping("/")
    String home(ModelMap model) {
        model.addAttribute("shelves", vendingMachine.getShelves());
        model.addAttribute("money", vendingMachine.getMoney());
        model.addAttribute("selectedShelve", vendingMachine.getSelectedShelve());

        return "vendingMachine";
    }

    @PostMapping("/selectShelve")
    public String selectShelve(@RequestParam("selectedShelve") int selectedShelve) throws Exception {
        vendingMachine.selectShelve(selectedShelve);
        return "redirect:/";
    }
}
