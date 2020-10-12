package dev.binarycoders.thermae.core.persistence.model;

import dev.binarycoders.thermae.core.ThermaeCoreConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Builder
@Table(name = "posts", schema = ThermaeCoreConstants.SCHEMA)
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Post name cannot be empty or Null")
    @Column(nullable = false)
    private String name;

    @Nullable
    private String url;

    @Nullable
    @Lob
    private String description;

    @Column(name = "vote_count", nullable = false)
    private Long voteCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subreddit_id", referencedColumnName = "id", nullable = false)
    private SubredditEntity subreddit;
}
