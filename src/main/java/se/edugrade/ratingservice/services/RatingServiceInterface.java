package se.edugrade.ratingservice.services;

import se.edugrade.ratingservice.dto.RatingRequestDTO;
import se.edugrade.ratingservice.dto.RatingResponseDTO;
import se.edugrade.ratingservice.dto.RatingStatsDTO;

import java.util.List;

public interface RatingServiceInterface {

    RatingResponseDTO create(RatingRequestDTO requestDTO);
    RatingResponseDTO updateExisting(Long id, RatingRequestDTO requestDTO);
    List<RatingResponseDTO> findByUserId(String userId);
    List<RatingResponseDTO> findByMediaId(String mediaId);
    void deleteById(Long id);

    RatingStatsDTO getMediaStats(String mediaId);
}

/**********
 * POST edufy/v1/ratings ==== Lägger till eller uppdaterar en befintlig rating
 GET edufy/v1/ratings/user/{userID} ==== Får alla ratings för en specifik användare
 GET edufy/v1/ratings/media/{mediaID} ==== Får ratings stats för en specifik media (antalet tummen upp/ner)
 DELETE edufy/v1/ratings/{id} ==== Raderar en rating
 ***********/