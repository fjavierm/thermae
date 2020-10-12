create table redditclone.users
(
    id         bigserial not null,
    created    timestamp not null,
    email      text      not null,
    enabled    bool      not null,
    "password" text      not null,
    username   text      not null,
    constraint users_pkey primary key (id)
);

create table redditclone.subreddits
(
    id          bigserial not null,
    created     timestamp not null,
    description text      not null,
    "name"      text      not null,
    user_id     bigserial not null,
    constraint subreddits_pkey primary key (id)
);

alter table redditclone.subreddits
    add constraint subreddits_users_fk foreign key (user_id) references redditclone.users (id);

create table redditclone.verification_tokens
(
    id          bigserial not null,
    expiry_date timestamp not null,
    "token"     text      not null,
    user_id     bigserial not null,
    constraint verification_tokens_pkey primary key (id)
);

alter table redditclone.verification_tokens
    add constraint verification_tokens_user_fk foreign key (user_id) references redditclone.users (id);

create table redditclone.posts
(
    id           bigserial not null,
    description  text      null,
    "name"       text      not null,
    url          text      null,
    vote_count   bigint    not null,
    subreddit_id bigserial not null,
    constraint posts_pkey primary key (id)
);

alter table redditclone.posts
    add constraint posts_subreddit_fk foreign key (subreddit_id) references redditclone.subreddits (id);

create table redditclone.subreddits_posts
(
    subreddit_id bigserial not null,
    posts_id     bigserial not null,
    constraint subreddits_posts_post_id_uk unique (posts_id)
);

alter table redditclone.subreddits_posts
    add constraint subreddits_posts_subreddit_fk foreign key (subreddit_id) references redditclone.subreddits (id);
alter table redditclone.subreddits_posts
    add constraint subreddits_posts_post_fk foreign key (posts_id) references redditclone.posts (id);

create table redditclone.votes
(
    id        bigserial not null,
    vote_type text      not null,
    post_id   bigserial not null,
    user_id   bigserial not null,
    constraint votes_pkey primary key (id)
);

alter table redditclone.votes
    add constraint votes_posts_fk foreign key (post_id) references redditclone.posts (id);
alter table redditclone.votes
    add constraint votes_users_fk foreign key (user_id) references redditclone.users (id);

create table redditclone."comments"
(
    id      bigserial not null,
    created timestamp not null,
    "text"  text      null,
    post_id bigserial not null,
    user_id bigserial not null,
    constraint comments_pkey primary key (id)
);

alter table redditclone."comments"
    add constraint comments_users_fk foreign key (user_id) references redditclone.users (id);
alter table redditclone."comments"
    add constraint comments_posts_fk foreign key (post_id) references redditclone.posts (id);
