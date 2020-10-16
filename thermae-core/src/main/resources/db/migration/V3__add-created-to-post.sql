alter table thermae.posts
    add column created timestamp not null default now();