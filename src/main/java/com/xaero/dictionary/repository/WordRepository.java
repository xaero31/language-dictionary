package com.xaero.dictionary.repository;

import com.xaero.dictionary.entity.WordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<WordEntity, Long> {
}
