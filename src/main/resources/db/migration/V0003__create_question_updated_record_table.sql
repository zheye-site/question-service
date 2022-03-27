CREATE TABLE question_updated_record
(
    question_id     text        NOT NULL,
    update_type     text        NOT NULL,
    updater_id      text        NOT NULL,
    updated_at      timestamptz NOT NULL,
    reason          text,
    created_title   text,
    created_detail  text,
    unedited_title  text,
    edited_title    text,
    unedited_detail text,
    edited_detail   text
);

CREATE INDEX question_updated_record_question_id_and_updated_at_index ON question_updated_record USING btree (question_id, updated_at);
