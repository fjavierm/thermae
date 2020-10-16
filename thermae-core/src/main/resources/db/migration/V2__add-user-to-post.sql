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