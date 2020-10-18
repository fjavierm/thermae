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

package dev.binarycoders.thermae.core.service.impl;

import dev.binarycoders.thermae.api.model.Comment;
import dev.binarycoders.thermae.core.exception.ThermaeException;
import dev.binarycoders.thermae.core.mapper.CommentMapper;
import dev.binarycoders.thermae.core.model.NotificationEmail;
import dev.binarycoders.thermae.core.persistence.model.PostEntity;
import dev.binarycoders.thermae.core.persistence.model.UserEntity;
import dev.binarycoders.thermae.core.persistence.repository.CommentRepository;
import dev.binarycoders.thermae.core.persistence.repository.PostRepository;
import dev.binarycoders.thermae.core.service.AuthService;
import dev.binarycoders.thermae.core.service.CommentService;
import dev.binarycoders.thermae.core.service.MailContentBuilderService;
import dev.binarycoders.thermae.core.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final CommentMapper COMMENT_MAPPER = CommentMapper.getInstance();

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final MailContentBuilderService mailContentBuilderService;
    private final MailService mailService;

    @Override
    public Comment save(final Comment comment) {
        final var commentEntity = COMMENT_MAPPER.toEntity(comment);
        final var user = authService.getCurrentUser();
        final var post = postRepository.findById(comment.getPostId())
            .orElseThrow(() -> new ThermaeException("Post not found to create a comment"));

        commentEntity.setUser(user);
        commentEntity.setPost(post);
        commentEntity.setCreated(Instant.now());

        if (post.getComments() == null) {
            post.setComments(new ArrayList<>());
        }
        post.getComments().add(commentEntity);

        postRepository.save(post);
        final var saved = commentRepository.save(commentEntity);

        comment.setId(saved.getId());

        sendCommentNotification(post, user); // TODO queue to send messages

        return comment;
    }

    private void sendCommentNotification(final PostEntity post, final UserEntity user) {
        final var message = mailContentBuilderService.build(
            String.format("%s posted a comment on you post. %s", user.getUsername(), post.getUrl()));

        mailService.sendMail(new NotificationEmail("You have a new comment", user.getEmail(), message));
    }

    @Override
    public List<Comment> getByPost(final Long postId) {
        return commentRepository.findByPostId(postId).stream()
            .map(COMMENT_MAPPER::toApi)
            .collect(Collectors.toList());
    }

    @Override
    public List<Comment> getByUser(final Long userId) {
        return commentRepository.findByUserId(userId).stream()
            .map(COMMENT_MAPPER::toApi)
            .collect(Collectors.toList());
    }
}
