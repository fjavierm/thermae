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

package dev.binarycoders.thermae.core.mapper;

import dev.binarycoders.thermae.api.model.Subreddit;
import dev.binarycoders.thermae.core.persistence.model.SubredditEntity;

public class SubredditMapper implements Mapper<Subreddit, SubredditEntity> {

    private static final SubredditMapper INSTANCE = new SubredditMapper();

    public static SubredditMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Subreddit toApi(final SubredditEntity entity) {
        if (entity == null) {
            return null;
        }

        return Subreddit.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .numberOfPosts(entity.getPosts().size())
            .build();
    }

    @Override
    public SubredditEntity toEntity(final Subreddit subreddit) {
        if (subreddit == null) {
            return null;
        }

        return SubredditEntity.builder()
            .id(subreddit.getId())
            .name(subreddit.getName())
            .description(subreddit.getDescription())
            .build();
    }
}
