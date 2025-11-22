package se.edugrade.ratingservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import se.edugrade.ratingservice.dto.RatingRequestDTO;
import se.edugrade.ratingservice.dto.RatingResponseDTO;
import se.edugrade.ratingservice.dto.RatingStatsDTO;
import se.edugrade.ratingservice.entities.Ratings;
import se.edugrade.ratingservice.repositories.RatingRepository;

import java.util.List;
import java.util.Optional;

public class RatingService implements RatingServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public RatingResponseDTO create(RatingRequestDTO requestDTO) {
        if (requestDTO.userId() == null || requestDTO.userId().isBlank()) {
            throw new IllegalStateException("userId is null or empty");
        }
        if (requestDTO.mediaId() == null || requestDTO.mediaId().isBlank()) {
            throw new IllegalStateException("mediaId is null or empty");
        }

        Optional<Ratings> existing = ratingRepository.findByUserIdAndMediaId(requestDTO.userId(), requestDTO.mediaId());
        if (existing.isPresent()) {
        }
    }

    @Override
    public RatingResponseDTO updateExisting(Long id, RatingRequestDTO requestDTO) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RatingResponseDTO> findByUserId(String userId) {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RatingResponseDTO> findByMediaId(String mediaId) {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    @Transactional(readOnly = true)
    public RatingStatsDTO getMediaStats(String mediaId) {
        return null;
    }
}
