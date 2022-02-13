package com.xaero.dictionary.controller;

import com.xaero.dictionary.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.data.domain.PageRequest.of;

@AllArgsConstructor
@Controller
public class NotLearnedWordsController {

    private static final String PAGE_TITLE = "Dictionary not learned words page";
    private static final int FIRST_PAGE = 0;
    private static final int PAGE_SIZE = 20;

    private final WordRepository repository;

    @RequestMapping("/notLearnedWords")
    public String notLearnedWords() {
        return "redirect:/notLearnedWords/" + FIRST_PAGE;
    }

    @RequestMapping("/notLearnedWords/{pageNumber}")
    public String notLearnedWords(@PathVariable int pageNumber, Model model) {
        model.addAttribute("title", PAGE_TITLE);
        model.addAttribute("page", repository.findAllNotLearnedWords(of(pageNumber, PAGE_SIZE)));
        return "all-words";
    }
}
