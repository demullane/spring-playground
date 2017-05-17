package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WordCounter {

  @Bean
  public WordCounter getWordCounter() { return this; }

  public Map<String, Integer> count(String sentence) {
    String[] words = sentence.split(" ");

    Map<String, Integer> counter = new HashMap<>();
    for (String word : words) {
      Integer count = counter.get(word) != null ? counter.get(word) : 0;
      counter.put(word, count + 1);
    }

    return counter;
  }
}
