package se.edugrade.ratingservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.edugrade.ratingservice.dto.RatingRequestDTO;
import se.edugrade.ratingservice.dto.RatingResponseDTO;
import se.edugrade.ratingservice.services.RatingService;
import org.springframework.security.core.Authentication;


import java.util.List;

@RestController
@RequestMapping("/edufy/v1/ratings")
public class CustomerController {

    private final RatingService ratingService;

    public CustomerController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rate")
    public ResponseEntity<RatingResponseDTO> createRating(@RequestBody RatingRequestDTO requestDTO, JwtAuthenticationToken auth) {
        var created = ratingService.create(requestDTO, auth);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/user/{userId}")
    public List<RatingResponseDTO> myUserRatings(@PathVariable String userId, Authentication authentication) {
        String authenticatedUserId = authentication.getName();

        if (!authenticatedUserId.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to view this users ratings");
        }
        return ratingService.findByUserId(userId);
    }
}
