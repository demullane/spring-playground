package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class WordsController {

  private WordCounter wordCounter;

  public WordsController(WordCounter wordCounter) {
    this.wordCounter = wordCounter;
  }

  @PostMapping("/words/count")
  public Map<String, Integer> getWordCounter(@RequestBody String sentence) {
    return this.wordCounter.count(sentence);
  }

}
