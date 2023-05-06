--liquibase formatted sql

--changeset alexsandrov:1
ALTER TABLE users
    ADD COLUMN created_at timestamp;

ALTER TABLE users
    ADD COLUMN modified_at timestamp;

ALTER TABLE users
    ADD COLUMN created_by VARCHAR(32);

ALTER TABLE users
    ADD COLUMN modified_by VARCHAR(32);