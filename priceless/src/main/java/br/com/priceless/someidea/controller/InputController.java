package br.com.priceless.someidea.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class InputController {

    @RequestMapping("/something")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
