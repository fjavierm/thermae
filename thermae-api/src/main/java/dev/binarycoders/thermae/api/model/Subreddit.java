package dev.binarycoders.thermae.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subreddit {

    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
