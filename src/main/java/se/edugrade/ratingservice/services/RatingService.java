package se.edugrade.ratingservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import se.edugrade.ratingservice.dto.RatingRequestDTO;
import se.edugrade.ratingservice.dto.RatingResponseDTO;
import se.edugrade.ratingservice.dto.RatingStatsDTO;
import se.edugrade.ratingservice.entities.Ratings;
import se.edugrade.ratingservice.repositories.RatingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements RatingServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);
    private final RatingRepository ratingRepository;
    private final DefaultAuthenticationEventPublisher authenticationEventPublisher;

    public RatingService(RatingRepository ratingRepository, DefaultAuthenticationEventPublisher authenticationEventPublisher) {
        this.ratingRepository = ratingRepository;
        this.authenticationEventPublisher = authenticationEventPublisher;
    }

    /***********CommonController*************/
    @Override
    public RatingResponseDTO create(RatingRequestDTO requestDTO, JwtAuthenticationToken auth) {
        String userId = auth.getName();

        Optional<Ratings> existing = ratingRepository.findByUserIdAndMediaId(userId, requestDTO.mediaId());
        if (existing.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You already rated this media");
        }
        Ratings rating = new Ratings();
        rating.setUserId(userId);
        rating.setMediaId(requestDTO.mediaId());
        rating.setLiked(requestDTO.liked());

        Ratings saved = ratingRepository.save(rating);

        logger.info(saved.toString());

        return new RatingResponseDTO(
                saved.getId(),
                saved.getUserId(),
                saved.getMediaId(),
                saved.isLiked()
                );
    }

    /************CustomerController***************/
    @Override
    @Transactional(readOnly = true)
    public List<RatingResponseDTO> findByUserId(String userId) {
       //Kontrollera användare
        return ratingRepository.findByUserId(userId)
                .stream()
                .map(r -> new RatingResponseDTO(
                        r.getId(),
                        r.getUserId(),
                        r.getMediaId(),
                        r.isLiked()))
                .toList();
    }

    /***********AdminController************/

    @Override
    public List<RatingResponseDTO> findAll() {
        if (ratingRepository.findAll().isEmpty()) {
            logger.warn("No Ratings found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ratingRepository.findAll()
                .stream()
                .map(r -> new RatingResponseDTO(
                        r.getId(),
                        r.getUserId(),
                        r.getMediaId(),
                        r.isLiked()
                ))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        if(!ratingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ratingRepository.deleteById(id);
        logger.info("Deleted rating with id: " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public RatingStatsDTO getMediaStats(String mediaId) {
        Long up = ratingRepository.countByMediaIdAndLiked(mediaId, true);
        Long down = ratingRepository.countByMediaIdAndLiked(mediaId, false);
        return new RatingStatsDTO(up, down);
    }
}
