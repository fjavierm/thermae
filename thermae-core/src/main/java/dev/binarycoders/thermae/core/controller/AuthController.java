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

import dev.binarycoders.thermae.api.request.LoginRequest;
import dev.binarycoders.thermae.api.request.RefreshTokenRequest;
import dev.binarycoders.thermae.api.request.RegisterRequest;
import dev.binarycoders.thermae.api.response.AuthenticationResponse;
import dev.binarycoders.thermae.core.service.AuthService;
import dev.binarycoders.thermae.core.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody final RegisterRequest request) {
        // TODO Add validation
        authService.signup(request.getUsername(), request.getEmail(), request.getPassword());

        return ResponseEntity.ok("User registration successful");
    }

    @GetMapping(value = "/account-verification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable final String token) {
        authService.verifyAccount(token);

        return ResponseEntity.ok("Account activated successfully");
    }

    @PostMapping(value = "/login")
    public AuthenticationResponse login(@RequestBody final LoginRequest request) {
        // TODO Add validation
        return authService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping(value = "/refhresh/token")
    public AuthenticationResponse refreshToken(@RequestBody final RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<String> logout(@RequestBody final RefreshTokenRequest request) {
        refreshTokenService.deleteRefreshToken(request.getRefreshToken());

        return ResponseEntity.ok("Refresh token deleted successfully.");
    }
}
