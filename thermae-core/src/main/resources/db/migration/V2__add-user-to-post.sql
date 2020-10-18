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

alter table thermae.posts
    add column user_id bigserial not null;

alter table thermae.posts
    add constraint posts_users_fk foreign key (user_id) references thermae.users (id);

create table thermae.users_posts
(
    user_id  bigserial not null,
    posts_id bigserial not null,
    constraint users_posts_post_id_uk unique (posts_id)
);

alter table thermae.users_posts
    add constraint users_posts_subreddit_fk foreign key (user_id) references thermae.users (id);
alter table thermae.users_posts
    add constraint users_posts_post_fk foreign key (posts_id) references thermae.posts (id);