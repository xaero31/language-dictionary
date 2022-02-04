CREATE TABLE IF NOT EXISTS words (
    id   INTEGER      PRIMARY KEY,
    native_word VARCHAR(128) NOT NULL,
    translated_word VARCHAR(128) NOT NULL
);