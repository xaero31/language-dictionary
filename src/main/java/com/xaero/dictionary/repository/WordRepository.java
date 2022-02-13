package com.xaero.dictionary.repository;

import com.xaero.dictionary.entity.WordEntity;
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

    Optional<WordEntity> findByNativeWord(String nativeWord);

    @Query(value = "select translated_word from words where translated_word <> :translatedWord order by random() limit :count",
            nativeQuery = true)
    List<String> findExclusiveTranslatedWords(String translatedWord, int count);
}
