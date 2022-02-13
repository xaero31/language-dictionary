package com.xaero.dictionary.test;

import com.xaero.dictionary.entity.WordEntity;
import com.xaero.dictionary.form.TestWordsForm;
import com.xaero.dictionary.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.shuffle;

@AllArgsConstructor
@Component
public class TestWordFormProducer {

    private final WordRepository wordRepository;

    public TestWordsForm prepareTestWordForm(int wordsCnt, int variantsCnt) {
        final Map<Long, TestWordsForm.TestWordFormItem> wordsMap = new HashMap<>();
        final List<WordEntity> words = wordRepository.findRandomNotLearnedWords(wordsCnt);

        words.forEach(word -> addWordItem(word, variantsCnt, wordsMap));

        return new TestWordsForm(wordsMap);
    }

    private void addWordItem(WordEntity word, int variantsCnt, Map<Long, TestWordsForm.TestWordFormItem> wordsMap) {
        final TestWordsForm.TestWordFormItem item = new TestWordsForm.TestWordFormItem();

        item.setWord(word);
        item.setResponseVariantList(wordRepository.findExclusiveTranslatedWords(word.getTranslatedWord(), variantsCnt));
        item.getResponseVariantList().add(word.getTranslatedWord());

        shuffle(item.getResponseVariantList());

        wordsMap.put(word.getId(), item);
    }
}
