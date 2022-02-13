package com.xaero.dictionary.repository;

import com.xaero.dictionary.entity.LearnedWordEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnedWordRepository extends PagingAndSortingRepository<LearnedWordEntity, Long> {

    @Query(value = "select w.id from LearnedWordEntity w")
    Iterable<Long> findAllIds();
}
