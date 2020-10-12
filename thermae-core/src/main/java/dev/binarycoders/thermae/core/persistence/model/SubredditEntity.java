package dev.binarycoders.thermae.core.persistence.model;

import dev.binarycoders.thermae.core.ThermaeConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "subreddits", schema = ThermaeConstants.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
public class SubredditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Community name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @Column(nullable = false, updatable = false)
    private Instant created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "subreddit", fetch = FetchType.LAZY)
    private List<PostEntity> posts;
}
