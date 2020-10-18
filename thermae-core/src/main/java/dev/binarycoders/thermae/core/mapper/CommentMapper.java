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

import dev.binarycoders.thermae.api.model.Comment;
import dev.binarycoders.thermae.core.persistence.model.CommentEntity;

public class CommentMapper implements Mapper<Comment, CommentEntity> {

    private static final CommentMapper INSTANCE = new CommentMapper();

    public static CommentMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Comment toApi(final CommentEntity entity) {
        return Comment.builder()
            .id(entity.getId())
            .text(entity.getText())
            .created(entity.getCreated())
            .postId(entity.getPost() != null ? entity.getPost().getId() : null)
            .userId(entity.getUser() != null ? entity.getUser().getId() : null)
            .build();
    }

    @Override
    public CommentEntity toEntity(final Comment comment) {
        return CommentEntity.builder()
            .id(comment.getId())
            .text(comment.getText())
            .created(comment.getCreated())
            .build();
    }
}
