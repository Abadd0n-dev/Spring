package com.example.practice2.conroller;

import org.springframework.stereotype.Controller;

@Controller
public class HelloWorldController {
    @RequestMapping(value = "/greeting")
    public String helloWorldController(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
