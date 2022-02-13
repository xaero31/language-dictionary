package com.xaero.dictionary.repository;

import com.xaero.dictionary.entity.WordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends PagingAndSortingRepository<WordEntity, Long> {

    @Query(value = "select * from words limit 1 offset abs(random()) % (select count(*) from words)", nativeQuery = true)
    WordEntity findRandom();

    @Query(value = "select * from words order by random() limit :count", nativeQuery = true)
    List<WordEntity> findRandomWords(int count);

    @Query(value = "select * from words where id not in (select word_id from learned_words) order by random() limit :count", nativeQuery = true)
    List<WordEntity> findRandomNotLearnedWords(int count);

    @Query(value = "select * from words where id not in (select word_id from learned_words)", nativeQuery = true)
    Page<WordEntity> findAllNotLearnedWords(Pageable pageable);

    Optional<WordEntity> findByNativeWord(String nativeWord);

    @Query(value = "select translated_word from words where translated_word <> :translatedWord order by random() limit :count",
            nativeQuery = true)
    List<String> findExclusiveTranslatedWords(String translatedWord, int count);

    @Query(value = "select * from words where native_word like %:word% union select * from words where translated_word like %:word%", nativeQuery = true)
    List<WordEntity> findByNativeWordOrTranslatedWord(String word);

    Page<WordEntity> findAllByIdIn(Pageable pageable, Iterable<Long> id);
}
