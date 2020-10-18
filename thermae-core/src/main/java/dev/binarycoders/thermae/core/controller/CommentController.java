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

import dev.binarycoders.thermae.api.model.Comment;
import dev.binarycoders.thermae.core.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody final Comment post) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(commentService.save(post));
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<List<Comment>> getByPost(@PathVariable final Long postId) {
        return ResponseEntity.ok(commentService.getByPost(postId));
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<Comment>> getByUser(@PathVariable final Long userId) {
        return ResponseEntity.ok(commentService.getByUser(userId));
    }
}
