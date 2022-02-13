package com.xaero.dictionary.controller;

import com.xaero.dictionary.form.TestWordsForm;
import com.xaero.dictionary.test.TestWordFormProducer;
import com.xaero.dictionary.validate.TestWordFormValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@AllArgsConstructor
@Controller
@SessionAttributes(names = "testWordsForm")
public class TestWordsController {

    private static final String PAGE_TITLE = "Dictionary test words page";

    private static final int WORDS_CNT = 6;
    private static final int ADDITIONAL_VARIANTS_CNT = 4;

    private final TestWordFormProducer formProducer;
    private final TestWordFormValidator formValidator;

    @GetMapping(path = "/testWords")
    public String testWords(Model model) {
        model.addAttribute("title", PAGE_TITLE);
        model.addAttribute("testWordsForm", formProducer.prepareTestWordForm(WORDS_CNT, ADDITIONAL_VARIANTS_CNT));
        return "test-words";
    }

    @PostMapping(path = "/testWords")
    public String testWords(@ModelAttribute TestWordsForm testWordsForm, Model model) {
        model.addAttribute("title", PAGE_TITLE);
        formValidator.fillTestWordFormResult(testWordsForm);
        return "test-words";
    }
}
