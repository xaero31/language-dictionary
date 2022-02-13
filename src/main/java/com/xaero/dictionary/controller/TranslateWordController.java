package com.xaero.dictionary.controller;

import com.xaero.dictionary.form.TranslateForm;
import com.xaero.dictionary.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@AllArgsConstructor
@Controller
@SessionAttributes("translateForm")
public class TranslateWordController {

    private static final String PAGE_TITLE = "Dictionary translate word page";

    private final WordRepository wordRepository;

    @GetMapping(path = "/translateWord")
    public String translateWord(Model model) {
        model.addAttribute("title", PAGE_TITLE);
        model.addAttribute("translateForm", new TranslateForm());
        return "translate";
    }

    @PostMapping(path = "/translateWord")
    public String translateWord(@ModelAttribute TranslateForm translateForm, Model model) {
        model.addAttribute("title", PAGE_TITLE);
        model.addAttribute("result", wordRepository.findByNativeWordOrTranslatedWord(translateForm.getWord()));
        return "translate";
    }
}
