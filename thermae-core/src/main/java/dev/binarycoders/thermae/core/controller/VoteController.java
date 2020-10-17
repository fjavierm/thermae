package dev.binarycoders.thermae.core.controller;

import dev.binarycoders.thermae.api.model.Vote;
import dev.binarycoders.thermae.core.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/votes")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody final Vote vote) {
        voteService.vote(vote);

        return ResponseEntity.ok().build();
    }
}
