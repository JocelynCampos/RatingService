package se.edugrade.ratingservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("edufy/v1/ratings")
public class AdminController {

    /********
     *
     * POST edufy/v1/ratings ==== Lägger till eller uppdaterar en befintlig rating
     * GET edufy/v1/ratings/user/{userID} ==== Får alla ratings för en specifik användare
     * GET edufy/v1/ratings/media/{mediaID} ==== Får ratings stats för en specifik media (antalet tummen upp/ner)
     * DELETE edufy/v1/ratings/{id} ==== Raderar en rating
     *
     *
     * **********/

    @PostMapping("/add")
    public String add() {
        return "add";
    }


    @GetMapping("/user/{uderId}")
    public String userRatings(@PathVariable String uderId) {
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


