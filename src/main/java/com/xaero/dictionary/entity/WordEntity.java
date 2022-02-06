package com.xaero.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotNull(message = "native word [${validatedValue}] cannot be empty")
    @Size(min = 1, message = "native word [${validatedValue}] value cannot be empty")
    @Pattern(regexp = "[a-z]*", message = "native word [${validatedValue}] should contain only lower case latin letters")
    @Column(name = "native_word", nullable = false)
    private String nativeWord;

    @NotNull(message = "translated word [${validatedValue}] cannot be empty")
    @Size(min = 1, message = "translated word [${validatedValue}] value cannot be empty")
    @Pattern(regexp = "[,a-zа-я\\s]*",
            message = "translated word [${validatedValue}] should contain only lower case letters, comas or spaces")
    @Column(name = "translated_word", nullable = false)
    private String translatedWord;
}
