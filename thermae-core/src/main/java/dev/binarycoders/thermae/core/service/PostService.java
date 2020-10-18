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

import dev.binarycoders.thermae.api.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post save(Post post);

    List<Post> getAll();

    Optional<Post> getById(Long id);

    List<Post> getBySubreddit(Long subredditId);

    List<Post> getByUser(Long userId);
}
