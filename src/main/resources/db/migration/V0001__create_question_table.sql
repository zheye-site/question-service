CREATE TABLE question
(
    id            serial PRIMARY KEY,
    questioner_id text NOT NULL,
    title         text NOT NULL,
    detail        text
);
