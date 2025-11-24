package se.edugrade.ratingservice.dto;

public record RatingRequestDTO(
        String mediaId,
        boolean liked
) {}


