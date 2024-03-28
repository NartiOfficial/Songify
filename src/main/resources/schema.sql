DROP TABLE  song;

CREATE TABLE IF NOT EXISTS song
(
    id          BIGSERIAL       PRIMARY KEY,
    artist      varchar(255)    NOT NULL,
    name        varchar(255)    NOT NULL
)



