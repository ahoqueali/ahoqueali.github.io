package org.hoque.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {
    
    @PostMapping("hello")
    public String hello(@RequestParam("name") String name){
        return "Hello " + name;
    }

}