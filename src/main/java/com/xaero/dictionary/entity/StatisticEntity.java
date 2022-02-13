package com.xaero.dictionary.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "statistics")
public class StatisticEntity {

    @Id
    @Column(name = "word_id")
    private long id;

    @Column(name = "try_cnt")
    private long tryCnt;

    @Column(name = "success_try_cnt")
    private long successTryCnt;

    public StatisticEntity(long id) {
        this.id = id;
    }
}
