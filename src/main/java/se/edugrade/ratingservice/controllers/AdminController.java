package se.edugrade.ratingservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.edugrade.ratingservice.dto.RatingResponseDTO;
import se.edugrade.ratingservice.dto.RatingStatsDTO;
import se.edugrade.ratingservice.services.RatingService;

import java.util.List;


@RestController
@RequestMapping("/edufy/v1/ratings")
public class AdminController {

    private final RatingService ratingService;

    public AdminController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/all")
    public List<RatingResponseDTO> getAllRatings() {
        return ratingService.findAll();
    }

    @GetMapping("/stats/{mediaId}")
    public RatingStatsDTO getStatistics(@PathVariable String mediaId) {
        return ratingService.getMediaStats(mediaId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


