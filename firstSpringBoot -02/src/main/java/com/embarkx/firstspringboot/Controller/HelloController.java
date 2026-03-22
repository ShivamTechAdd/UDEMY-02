package com.embarkx.firstspringboot.Controller;

import com.embarkx.firstspringboot.Model.helloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public helloResponse hello(){
        return new helloResponse("Hello world");
    }

    @PostMapping("/post")
    public String helloPost(@RequestBody String name){
        return "Hello:- "+name;
    }

    

}
