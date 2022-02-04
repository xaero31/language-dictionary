package com.xaero.dictionary.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "words")
public class WordEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "native_word", nullable = false)
    private String nativeWord;

    @Column(name = "translated_word", nullable = false)
    private String translatedWord;
}
