create table thermae.refresh_tokens
(
    id      bigserial not null,
    token   text      not null,
    created timestamp not null,

    constraint refresh_token_pkey primary key (id)
)