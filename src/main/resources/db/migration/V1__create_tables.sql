
CREATE TABLE employer(
                         id      BIGSERIAL PRIMARY KEY,
                         email   TEXT UNIQUE NOT NULL,
                         name    TEXT        NOT NULL,
                         website TEXT        NOT NULL
);

CREATE TABLE candidate
(
    id         BIGSERIAL PRIMARY KEY,
    email      TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    resume     TEXT NOT NULL
);

CREATE UNIQUE INDEX ON candidate (lower(email));


CREATE TABLE job
(
    id          BIGSERIAL PRIMARY KEY,
    employer_id BIGINT    NOT NULL REFERENCES employer,
    title       TEXT      NOT NULL,
    description TEXT      NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    active      BOOLEAN   NOT NULL
);


CREATE TABLE response
(
    id           BIGSERIAL PRIMARY KEY,
    job_id       BIGINT    NOT NULL REFERENCES job,
    candidate_id BIGINT    NOT NULL REFERENCES candidate,
    message      TEXT      NOT NULL,
    created_at   TIMESTAMP NOT NULL
);
