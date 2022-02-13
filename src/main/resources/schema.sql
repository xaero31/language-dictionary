CREATE TABLE IF NOT EXISTS words (
    id                  INTEGER PRIMARY KEY,
    native_word         VARCHAR(128) NOT NULL UNIQUE,
    translated_word     VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS statistics (
    word_id             INTEGER PRIMARY KEY,
    try_cnt             INTEGER DEFAULT 0,
    success_try_cnt     INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS learned_words (
    word_id             INTEGER PRIMARY KEY
);