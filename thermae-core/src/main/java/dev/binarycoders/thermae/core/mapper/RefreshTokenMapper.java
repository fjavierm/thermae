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

import dev.binarycoders.thermae.api.model.RefreshToken;
import dev.binarycoders.thermae.core.persistence.model.RefreshTokenEntity;

public class RefreshTokenMapper implements Mapper<RefreshToken, RefreshTokenEntity> {

    private static final RefreshTokenMapper INSTANCE = new RefreshTokenMapper();

    public static RefreshTokenMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public RefreshToken toApi(final RefreshTokenEntity entity) {
        if (entity == null) {
            return null;
        }

        return RefreshToken.builder()
            .token(entity.getToken())
            .build();
    }

    @Override
    public RefreshTokenEntity toEntity(final RefreshToken refreshToken) {
        if (refreshToken == null) {
            return null;
        }

        return RefreshTokenEntity.builder()
            .token(refreshToken.getToken())
            .build();
    }
}
