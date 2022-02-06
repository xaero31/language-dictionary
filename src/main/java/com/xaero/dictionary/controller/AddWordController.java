package com.xaero.dictionary.controller;

import com.xaero.dictionary.entity.WordEntity;
import com.xaero.dictionary.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class AddWordController {

    private final WordRepository repository;

    public AddWordController(WordRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/addWord", method = GET)
    public String addWord(Model model) {
        model.addAttribute("word", new WordEntity());
        return "add-word";
    }

    @RequestMapping(path = "/addWord", method = POST)
    public String addWord(@ModelAttribute WordEntity word, RedirectAttributes attributes) {
        if (!repository.findByNativeWord(word.getNativeWord()).isPresent()) {
            repository.save(word);
        } else {
            attributes.addFlashAttribute("error",
                    format("word '%s' already exists in dictionary", word.getNativeWord()));
        }

        return "redirect:addWord";
    }
}
