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

import dev.binarycoders.thermae.api.model.Subreddit;
import dev.binarycoders.thermae.core.mapper.SubredditMapper;
import dev.binarycoders.thermae.core.persistence.repository.SubredditRepository;
import dev.binarycoders.thermae.core.service.AuthService;
import dev.binarycoders.thermae.core.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditServiceImpl implements SubredditService {

    private static final SubredditMapper SUBREDDIT_MAPPER = SubredditMapper.getInstance();

    private final SubredditRepository subredditRepository;
    private final AuthService authService;

    @Override
    @Transactional
    public Subreddit save(final Subreddit subreddit) {
        final var subredditEntity = SUBREDDIT_MAPPER.toEntity(subreddit);
        final var user = authService.getCurrentUser();

        subredditEntity.setCreated(Instant.now());
        subredditEntity.setUser(user);

        final var saved = subredditRepository.save(subredditEntity);

        subreddit.setId(saved.getId());

        return subreddit;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subreddit> getAll() {
        return subredditRepository.findAll().stream()
            .map(SUBREDDIT_MAPPER::toApi)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Subreddit> getById(final Long id) {
        final var entity = subredditRepository.findById(id);

        return entity.map(SUBREDDIT_MAPPER::toApi);
    }
}
