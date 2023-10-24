package com.simran.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping
public class homepage {
    @GetMapping("/")
    public String homefunc() {
        return "home";
    }
}
