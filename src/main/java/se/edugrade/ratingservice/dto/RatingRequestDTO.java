package se.edugrade.ratingservice.dto;

public record RatingRequestDTO(
        String userId,
        String mediaId,
        boolean liked
) {}


