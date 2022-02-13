package com.xaero.dictionary.validate;

import com.xaero.dictionary.entity.WordEntity;
import com.xaero.dictionary.form.TestWordsForm;
import com.xaero.dictionary.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@AllArgsConstructor
@Component
public class TestWordFormValidator {

    private final WordRepository wordRepository;

    public void fillTestWordFormResult(TestWordsForm form) {
        form.getWordMap()
                .values()
                .forEach(this::fillTestWordResult);
    }

    public void fillTestWordResult(TestWordsForm.TestWordFormItem wordItem) {
        final WordEntity word = wordRepository.findById(wordItem.getWord().getId())
                .orElseThrow(() -> new IllegalArgumentException(format("unknown word id %d", wordItem.getWord().getId())));
        wordItem.setIsCorrect(word.getTranslatedWord().equals(wordItem.getWord().getTranslatedWord()));
    }
}
