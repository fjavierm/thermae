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

package dev.binarycoders.thermae.core.service;

import dev.binarycoders.thermae.api.request.RefreshTokenRequest;
import dev.binarycoders.thermae.api.response.AuthenticationResponse;
import dev.binarycoders.thermae.core.persistence.model.UserEntity;

public interface AuthService {
    void signup(String username, String email, String password);

    void verifyAccount(String token);

    AuthenticationResponse login(String username, String password);

    UserEntity getCurrentUser();

    AuthenticationResponse refreshToken(RefreshTokenRequest request);
}
