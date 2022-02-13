package com.xaero.dictionary.controller;

import com.xaero.dictionary.repository.LearnedWordRepository;
import com.xaero.dictionary.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.data.domain.PageRequest.of;

@AllArgsConstructor
@Controller
public class LearnedWordsController {

    private static final int FIRST_PAGE = 0;
    private static final int PAGE_SIZE = 20;

    private final WordRepository wordRepository;
    private final LearnedWordRepository learnedWordRepository;

    @RequestMapping("/learnedWords")
    public String learnedWords() {
        return "redirect:/learnedWords/" + FIRST_PAGE;
    }

    @RequestMapping("/learnedWords/{pageNumber}")
    public String learnedWords(@PathVariable int pageNumber, Model model) {
        model.addAttribute("title", "Dictionary learned words page");
        model.addAttribute("page",
                wordRepository.findAllByIdIn(of(pageNumber, PAGE_SIZE), learnedWordRepository.findAllIds()));
        return "all-words";
    }
}
