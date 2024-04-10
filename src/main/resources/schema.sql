-- DROP TABLE  song;
--
-- CREATE TABLE IF NOT EXISTS song
-- (
--     id          BIGSERIAL       PRIMARY KEY,
--     artist      varchar(255)    NOT NULL,
--     name        varchar(255)    NOT NULL
-- )

CREATE TABLE song
(
    id           BIGINT       PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    artist       VARCHAR(255) NOT NULL,
    release_date TIMESTAMP WITHOUT TIME ZONE,
    duration     BIGINT,
    language     VARCHAR(255),
);
