CREATE TABLE IF NOT EXISTS words (
    id   INTEGER      PRIMARY KEY,
    native_word VARCHAR(128) NOT NULL UNIQUE,
    translated_word VARCHAR(128) NOT NULL
);