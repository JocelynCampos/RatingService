package se.edugrade.ratingservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edufy/v1/ratings")
public class PingController {

    @GetMapping("/test")
    public String test() {
        return "Ping";
    }
}
