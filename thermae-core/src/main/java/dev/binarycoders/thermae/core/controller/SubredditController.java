package dev.binarycoders.thermae.core.controller;

import dev.binarycoders.thermae.api.model.Subreddit;
import dev.binarycoders.thermae.core.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/subreddits")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<Subreddit> create(@RequestBody final Subreddit subreddit) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(subredditService.save(subreddit));
    }

    @GetMapping
    public ResponseEntity<List<Subreddit>> getAll() {
        return ResponseEntity.ok(subredditService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Subreddit> get(@PathVariable final Long id) {
        final var subreddit = subredditService.getById(id);

        return subreddit.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
