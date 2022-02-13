package com.xaero.dictionary.form;

import com.xaero.dictionary.entity.WordEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestWordsForm {

    private Map<Long, TestWordFormItem> wordMap;

    @Data
    public static class TestWordFormItem {

        private WordEntity word;
        private List<String> responseVariantList;
        private String result;
        private Boolean isCorrect;
    }
}
