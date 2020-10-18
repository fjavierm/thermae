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

import dev.binarycoders.thermae.api.model.Vote;
import dev.binarycoders.thermae.core.persistence.model.VoteEntity;
import dev.binarycoders.thermae.core.persistence.model.VoteType;

public class VoteMapper implements Mapper<Vote, VoteEntity> {

    private static final VoteMapper INSTANCE = new VoteMapper();

    public static VoteMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Vote toApi(final VoteEntity entity) {
        return Vote.builder()
            .direction(entity.getType().getDirection())
            .userId(entity.getUser() != null ? entity.getUser().getId() : null)
            .postId(entity.getPost() != null ? entity.getPost().getId() : null)
            .build();
    }

    @Override
    public VoteEntity toEntity(final Vote vote) {
        return VoteEntity.builder()
            .type(VoteType.lookup(vote.getDirection()))
            .build();
    }
}
