package dev.binarycoders.thermae.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Long id;
    private String name;
    private String url;
    private String description;
    private Instant created;
    private Long userId;
    private Long subredditId;
    private Long voteCount;
    private Integer commentCount;
    private String duration; // TODO Needs some time ago message generator
}
