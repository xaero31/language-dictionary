package com.xaero.dictionary.repository;

import com.xaero.dictionary.entity.WordEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<WordEntity, Long> {

    @Query(value = "select * from words limit 1 offset abs(random()) % (select count(*) from words)", nativeQuery = true)
    WordEntity findRandom();
}
