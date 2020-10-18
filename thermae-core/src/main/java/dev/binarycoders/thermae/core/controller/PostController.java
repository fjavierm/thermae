/*
 * MIT License
 *
 * Copyright (c) 2020 fjavierm
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package dev.binarycoders.thermae.core.controller;

import dev.binarycoders.thermae.api.model.Post;
import dev.binarycoders.thermae.core.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody final Post post) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(postService.save(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> get(@PathVariable final Long id) {
        final var post = postService.getById(id);

        return post.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(value = "/subreddit/{subredditId}")
    public ResponseEntity<List<Post>> getBySubreddit(@PathVariable final Long subredditId) {
        return ResponseEntity.ok(postService.getBySubreddit(subredditId));
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<Post>> getByUser(@PathVariable final Long userId) {
        return ResponseEntity.ok(postService.getByUser(userId));
    }
}
