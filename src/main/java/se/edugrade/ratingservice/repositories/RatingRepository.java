package se.edugrade.ratingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.edugrade.ratingservice.entities.Ratings;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Ratings, Long> {

    List<Ratings> findByUserId(String userId);
    Optional<Ratings> findByUserIdAndMediaId(String userId, String mediaId);
}
