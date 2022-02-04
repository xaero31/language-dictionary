package com.xaero.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "words")
@AllArgsConstructor
@NoArgsConstructor
public class WordEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "native_word", nullable = false)
    private String nativeWord;

    @Column(name = "translated_word", nullable = false)
    private String translatedWord;
}
