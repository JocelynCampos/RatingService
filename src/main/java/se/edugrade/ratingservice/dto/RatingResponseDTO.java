package se.edugrade.ratingservice.dto;

public record RatingResponseDTO(
        Long id,
        String userId,
        String mediaId,
        boolean liked
) {}
