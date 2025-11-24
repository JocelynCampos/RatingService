package se.edugrade.ratingservice.services;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import se.edugrade.ratingservice.dto.RatingRequestDTO;
import se.edugrade.ratingservice.dto.RatingResponseDTO;
import se.edugrade.ratingservice.dto.RatingStatsDTO;
import org.springframework.security.core.Authentication;


import java.util.List;

public interface RatingServiceInterface {

    RatingResponseDTO create(RatingRequestDTO requestDTO, JwtAuthenticationToken auth);
    List<RatingResponseDTO> findAll();
    List<RatingResponseDTO> findByUserId(String userId);
    void deleteById(Long id);
    RatingStatsDTO getMediaStats(String mediaId);
}