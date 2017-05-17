package com.example;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class WordCounterTest {

  @Test
  public void testCount() throws Exception {
    WordCounter wordCounter = new WordCounter();
    Map<String, Integer> response = wordCounter.count("A brown cow jumps over a brown fox");
    String blah = "blah";
    assert(response.get("over").equals(1));
  }

}