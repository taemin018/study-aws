package com.example.controller.controller.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleForRestController {
    @GetMapping("/ex01")
    public String ex01() {
        return "/ex01";
    }
}
