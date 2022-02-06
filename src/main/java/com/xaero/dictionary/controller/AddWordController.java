package com.xaero.dictionary.controller;

import com.xaero.dictionary.entity.WordEntity;
import com.xaero.dictionary.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static java.text.MessageFormat.format;
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
        try {
            if (!repository.findByNativeWord(word.getNativeWord()).isPresent()) {
                repository.save(word);
                attributes.addFlashAttribute("success",
                        format("The [{0}] word saved to dictionary", word.getNativeWord()));
            } else {
                attributes.addFlashAttribute("error",
                        format("The [{0}] word already exists in dictionary", word.getNativeWord()));
            }
        } catch (Exception e) {
            log.error("saving '{}' word error: {}", word, e);
            attributes.addFlashAttribute("error",
                    format("Got tech error while adding the [{0}] word - [{1}:{2}]",
                            word.getNativeWord(), e.getClass().getName(), e.getMessage()));
        }

        return "redirect:addWord";
    }
}
