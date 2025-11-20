package se.edugrade.ratingservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings",
uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "media_id"}))

public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "media_id", nullable = false, unique = true)
    private String mediaId;

    @Column(nullable = false)
    private boolean liked;

}
