package se.edugrade.ratingservice;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ResponseStatusException;
import se.edugrade.ratingservice.dto.RatingRequestDTO;
import se.edugrade.ratingservice.dto.RatingResponseDTO;
import se.edugrade.ratingservice.entities.Ratings;
import se.edugrade.ratingservice.repositories.RatingRepository;
import se.edugrade.ratingservice.services.RatingService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    RatingRepository rateRepo;
    @InjectMocks
    RatingService rateService;

    private static Ratings ratings(Long id, String userId, String mediaId, boolean liked) {
        Ratings r = new Ratings();
        r.setId(id);
        r.setUserId(userId);
        r.setMediaId(mediaId);
        r.setLiked(liked);

        return r;
    }

    /*********************FindAll*************************/
    @Test
    void getAllRatings() {
        when(rateRepo.findAll()).thenReturn(List.of(ratings(1L, "user1", "media1", true), ratings(2L, "user2", "media2", false)));
        List<RatingResponseDTO> result = rateService.findAll();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals("user1", result.get(0).userId());
        assertEquals(2L, result.get(1).id());
        assertEquals("user2", result.get(1).userId());
        verify(rateRepo).findAll();
    }
    @Test
    void findAll_returnEmptyList_ifListEmpty() {
        when (rateRepo.findAll()).thenReturn(List.of());
        List<RatingResponseDTO> result = rateService.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(rateRepo).findAll();
    }

    /*************Create******************/
    @Test
    void createSuccessfully() {
        JwtAuthenticationToken auth = mock(JwtAuthenticationToken.class);
        when (auth.getName()).thenReturn("user1");

        RatingRequestDTO rq = new RatingRequestDTO("MovieOne", true);

        when (rateRepo.findByUserIdAndMediaId("user1", "MovieOne"))
                .thenReturn(Optional.empty());

        Ratings saved = new Ratings();
        saved.setId(1L);
        saved.setUserId("user1");
        saved.setMediaId("MovieOne");
        saved.setLiked(true);

        when(rateRepo.save(any(Ratings.class))).thenReturn(saved);

        RatingResponseDTO res = rateService.create(rq, auth);

        assertNotNull(res);
        assertEquals(1L, res.id());
        assertEquals("user1", res.userId());
        assertEquals("MovieOne", res.mediaId());
        assertTrue(res.liked());
        verify(rateRepo).findByUserIdAndMediaId("user1", "MovieOne");
        verify(rateRepo).save(any(Ratings.class));
    }






    /*************Delete******************/

    @Test
    void removeRating() throws Exception {
        when (rateRepo.existsById(1L)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> rateService.deleteById(1L));

        verify(rateRepo).existsById(1L);
        verify(rateRepo, never()).deleteById(anyLong());
        verify(rateRepo, never()).save(any());
    }

}
