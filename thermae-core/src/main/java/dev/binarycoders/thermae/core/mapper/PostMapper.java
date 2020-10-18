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

import dev.binarycoders.thermae.api.model.Post;
import dev.binarycoders.thermae.core.persistence.model.PostEntity;

public class PostMapper implements Mapper<Post, PostEntity> {

    private static final PostMapper INSTANCE = new PostMapper();

    public static PostMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Post toApi(final PostEntity entity) {
        if (entity == null) {
            return null;
        }

        return Post.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .created(entity.getCreated())
            .url(entity.getUrl())
            .voteCount(entity.getVoteCount())
            .commentCount(entity.getComments() != null ? entity.getComments().size() : 0)
            .duration("todo")  // TODO Needs some time ago message generator
            .userId(entity.getUser() != null ? entity.getUser().getId() : null)
            .subredditId(entity.getSubreddit() != null ? entity.getSubreddit().getId() : null)
            .build();
    }

    @Override
    public PostEntity toEntity(final Post post) {
        if (post == null) {
            return null;
        }

        return PostEntity.builder()
            .id(post.getId())
            .name(post.getName())
            .description(post.getDescription())
            .created(post.getCreated())
            .url(post.getUrl())
            .voteCount(post.getVoteCount())
            .build();
    }
}
