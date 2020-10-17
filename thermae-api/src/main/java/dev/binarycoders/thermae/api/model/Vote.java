package dev.binarycoders.thermae.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    private Integer direction;
    private Long userId;
    private Long postId;
}
