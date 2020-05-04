package org.hoque.demo;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {
    
    @GetMapping("hello")
    public String hello(@RequestParam("name") @Size(min = 5, max = 20) final String name) {
        return "Hello " + name;
    }

}