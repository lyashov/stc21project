package ru.innopolis.stc21.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String listUsers(Model model){
        //model.addAttribute("hello", "hello medProject!!!");
        return "index";
    }
}
