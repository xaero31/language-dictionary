package com.xaero.dictionary.controller;

import com.xaero.dictionary.repository.WordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.data.domain.PageRequest.of;

@Controller
public class AllWordsController {

    private static final int FIRST_PAGE = 0;
    private static final int PAGE_SIZE = 24;

    private final WordRepository repository;

    public AllWordsController(WordRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/allWords")
    public String allWords(Model model) {
        return allWords(FIRST_PAGE, model);
    }

    @RequestMapping("/allWords/{pageNumber}")
    public String allWords(@PathVariable int pageNumber, Model model) {
        model.addAttribute("page", repository.findAll(of(pageNumber, PAGE_SIZE)));
        return "all-words";
    }
}