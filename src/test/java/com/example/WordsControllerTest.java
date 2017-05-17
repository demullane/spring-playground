package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordsController.class)
public class WordsControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testGetWordCounter() throws Exception {
    MockHttpServletRequestBuilder request = post("/words/count")
        .contentType(MediaType.APPLICATION_JSON)
        .content("A brown cow jumps over a brown fox");

    this.mvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().string("{\"A\":1,\"brown\":2,\"cow\":1,\"jumps\":1,\"over\":1,\"a\":1,\"fox\":1}"));
  }

}