package com.xaero.dictionary.repository;

import com.xaero.dictionary.entity.WordEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends CrudRepository<WordEntity, Long> {

    @Query(value = "select * from words limit 1 offset abs(random()) % (select count(*) from words)", nativeQuery = true)
    WordEntity findRandom();

    @Query(value = "select * from words order by random() limit :count", nativeQuery = true)
    List<WordEntity> findRandomWords(int count);
}
