package com.xaero.dictionary.statistic;

import com.xaero.dictionary.entity.LearnedWordEntity;
import com.xaero.dictionary.entity.StatisticEntity;
import com.xaero.dictionary.form.TestWordsForm;
import com.xaero.dictionary.repository.LearnedWordRepository;
import com.xaero.dictionary.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;
import static java.util.stream.StreamSupport.stream;

@RequiredArgsConstructor
@Component
public class TestStatisticsAnalyser {

    private static final double LEARNED_RATE = 0.9;
    private static final int LEARNED_MIN_TRIES = 10;

    private final StatisticsRepository statisticsRepository;
    private final LearnedWordRepository learnedWordRepository;

    @Transactional
    public void saveTestStatistics(TestWordsForm wordsForm) {
        final List<StatisticEntity> updateStatisticList = prepareUpdateStatisticList(wordsForm);
        statisticsRepository.saveAll(updateStatisticList);

        final List<LearnedWordEntity> learnedWordList = prepareLearnedWordList(updateStatisticList);
        learnedWordRepository.saveAll(learnedWordList);
    }

    private List<StatisticEntity> prepareUpdateStatisticList(TestWordsForm wordsForm) {
        final Map<Long, TestWordsForm.TestWordFormItem> formItemMap = wordsForm.getWordMap();
        final Iterable<StatisticEntity> statisticIterable = statisticsRepository.findAllById(formItemMap.keySet());
        final Set<Long> statisticIdSet = stream(statisticIterable.spliterator(), false)
                .map(StatisticEntity::getId)
                .collect(toSet());

        final Stream<StatisticEntity> newStatisticStream = formItemMap.keySet()
                .stream()
                .filter(id -> !statisticIdSet.contains(id))
                .map(StatisticEntity::new);

        return concat(newStatisticStream, stream(statisticIterable.spliterator(), false))
                .peek(statistic -> statistic.setTryCnt(statistic.getTryCnt() + 1))
                .peek(statistic -> statistic.setSuccessTryCnt(resolveSuccessCnt(formItemMap, statistic)))
                .collect(toList());
    }

    private long resolveSuccessCnt(Map<Long, TestWordsForm.TestWordFormItem> formItemMap, StatisticEntity statistic) {
        return formItemMap.get(statistic.getId()).getIsCorrect() ?
                statistic.getSuccessTryCnt() + 1 :
                statistic.getSuccessTryCnt();
    }

    private boolean isEnoughTries(StatisticEntity statistic) {
        return statistic.getTryCnt() >= LEARNED_MIN_TRIES;
    }

    private boolean isLearned(StatisticEntity statistic) {
        return ((double) statistic.getSuccessTryCnt() / (double) statistic.getTryCnt()) >= LEARNED_RATE;
    }

    private List<LearnedWordEntity> prepareLearnedWordList(List<StatisticEntity> updateStatisticList) {
        return updateStatisticList.stream()
                .filter(this::isEnoughTries)
                .filter(this::isLearned)
                .map(StatisticEntity::getId)
                .map(LearnedWordEntity::new)
                .collect(toList());
    }
}
