package se.edugrade.ratingservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("edufy/v1/ratings")
public class AdminController {

    @PostMapping("/add")
    public String add() {
        return "add";
    }


    @GetMapping("/user/{userId}")
    public String userRatings(@PathVariable String userId) {
        return "userRatings";
    }

    @GetMapping("/media/{mediaId}")
    public String mediaRatings(@PathVariable String mediaId) {
        return "mediaRatings";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable String id) {
        return "deleteRating";
    }
}


