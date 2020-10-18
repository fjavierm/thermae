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

import dev.binarycoders.thermae.api.model.Vote;
import dev.binarycoders.thermae.core.exception.ThermaeException;
import dev.binarycoders.thermae.core.mapper.VoteMapper;
import dev.binarycoders.thermae.core.persistence.model.VoteEntity;
import dev.binarycoders.thermae.core.persistence.repository.PostRepository;
import dev.binarycoders.thermae.core.persistence.repository.VoteRepository;
import dev.binarycoders.thermae.core.service.AuthService;
import dev.binarycoders.thermae.core.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private static final VoteMapper VOTE_MAPPER = VoteMapper.getInstance();

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(Vote vote) {
        final var voteEntity = VOTE_MAPPER.toEntity(vote);
        final var user = authService.getCurrentUser();
        final var post = postRepository.findById(vote.getPostId())
            .orElseThrow(() -> new ThermaeException(String.format("Post not found with id %s", vote.getPostId())));
        final Optional<VoteEntity> top = voteRepository.findTopByPostAndUserOrderByIdDesc(post, user);

        if (top.isPresent() && Objects.equals(top.get().getType(), voteEntity.getType())) {
            throw new ThermaeException("Vote already counted");
        }

        post.setVoteCount(post.getVoteCount() + voteEntity.getType().getDirection());

        voteEntity.setUser(user);
        voteEntity.setPost(post);

        voteRepository.save(voteEntity);
        postRepository.save(post);
    }
}
