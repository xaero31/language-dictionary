package com.xaero.dictionary.repository;

import com.xaero.dictionary.entity.StatisticEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends PagingAndSortingRepository<StatisticEntity, Long> {
}
