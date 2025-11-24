package se.edugrade.ratingservice;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.edugrade.ratingservice.entities.Ratings;
import se.edugrade.ratingservice.repositories.RatingRepository;
import se.edugrade.ratingservice.services.RatingService;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    RatingRepository repo;
    @InjectMocks
    RatingService service;

    private static Ratings ratings(Long id, String userId, String mediaId, boolean liked) {
        Ratings r = new Ratings();
        r.setUserId(userId);
        r.setMediaId(mediaId);
        r.setLiked(liked);
        return r;
    }

}
