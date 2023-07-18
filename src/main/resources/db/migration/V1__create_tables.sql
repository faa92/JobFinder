
CREATE TABLE employer (
                          id BIGSERIAL PRIMARY KEY,
                          email TEXT UNIQUE NOT NULL,
                          name TEXT NOT NULL,
                          website TEXT NOT NULL
);

CREATE TABLE candidate (
                           id BIGSERIAL PRIMARY KEY,
                           email TEXT UNIQUE NOT NULL,
                           first_name TEXT NOT NULL,
                           last_name TEXT NOT NULL,
                           resume TEXT NOT NULL
);


CREATE TABLE job (
                     id BIGSERIAL PRIMARY KEY,
                     employer_id BIGINT NOT NULL,
                     title TEXT NOT NULL,
                     description TEXT NOT NULL,
                     active BOOLEAN NOT NULL,
                     FOREIGN KEY (employer_id) REFERENCES employer (id)
);


CREATE TABLE response (
                          id BIGSERIAL PRIMARY KEY,
                          job_id BIGINT NOT NULL,
                          candidate_id BIGINT NOT NULL,
                          message TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (job_id) REFERENCES job (id),
                          FOREIGN KEY (candidate_id) REFERENCES candidate (id)
);
