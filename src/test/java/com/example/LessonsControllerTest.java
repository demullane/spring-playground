package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTest {

  @Autowired
  MockMvc mvc;

  @Autowired
  LessonRepository repository;

  @Test
  @Transactional
  @Rollback
  public void testCreate() throws Exception {
    MockHttpServletRequestBuilder request = post("/lessons")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\": \"Endpoints\", \"deliveredOn\": \"2017-05-02\"}");

    this.mvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", instanceOf(Number.class)))
        .andExpect(jsonPath("$.title", equalTo("Endpoints")))
        .andExpect(jsonPath("$.deliveredOn", equalTo("2017-05-02")));
  }

  @Test
  @Transactional
  @Rollback
  public void testShow() throws Exception {
    String title = "Endpoints";
    Date date = new Date();

    Lesson lesson = new Lesson();
    lesson.setTitle(title);
    lesson.setDeliveredOn(date);
    repository.save(lesson);

    MockHttpServletRequestBuilder request = get("/lessons/" + lesson.getId().intValue())
        .contentType(MediaType.APPLICATION_JSON);

    this.mvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(lesson.getId().intValue())))
        .andExpect(jsonPath("$.title", equalTo(lesson.getTitle())));
  }
}