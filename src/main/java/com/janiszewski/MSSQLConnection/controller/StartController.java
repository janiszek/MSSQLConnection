package com.janiszewski.MSSQLConnection.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class StartController {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value="/welcome" , method = RequestMethod.GET)
    public String welcome() {
        return "index";
    }

    @RequestMapping(value="/bye" , method = RequestMethod.GET)
    public String bye() {
        return "bye";
    }

}
