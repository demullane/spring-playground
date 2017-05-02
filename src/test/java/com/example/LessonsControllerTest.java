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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
        .andExpect(jsonPath("$.title", equalTo(lesson.getTitle())))
        .andExpect(jsonPath("$.deliveredOn").doesNotExist());
  }

  @Test
  @Transactional
  @Rollback
  public void testUpdate() throws Exception {
    String title = "Endpoints";

    String inputString = "2017-05-02";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = dateFormat.parse(inputString);

    Lesson lesson = new Lesson();
    lesson.setTitle(title);
    lesson.setDeliveredOn(date);
    repository.save(lesson);

    MockHttpServletRequestBuilder request = patch("/lessons/" + lesson.getId().intValue())
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\": \"Spring Security\", \"deliveredOn\": \"2017-04-12\"}");

    this.mvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(lesson.getId().intValue())))
        .andExpect(jsonPath("$.title", equalTo("Spring Security")))
        .andExpect(jsonPath("$.deliveredOn", equalTo("2017-04-12")));
  }

  @Test
  @Transactional
  @Rollback
  public void testFindByTitle() throws Exception {
    String title = "Endpoints";

    String inputString = "2017-05-02";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = dateFormat.parse(inputString);

    Lesson lesson = new Lesson();
    lesson.setTitle(title);
    lesson.setDeliveredOn(date);
    repository.save(lesson);

    MockHttpServletRequestBuilder request = get("/lessons/find/" + lesson.getTitle())
        .contentType(MediaType.APPLICATION_JSON);

    this.mvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(lesson.getId().intValue())))
        .andExpect(jsonPath("$.title", equalTo(lesson.getTitle())))
        .andExpect(jsonPath("$.deliveredOn", equalTo(inputString)));
  }

  @Test
  @Transactional
  @Rollback
  public void testFindAllBetweenDates() throws Exception {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    String title1 = "Endpoints1";

    String inputString1 = "2017-05-02";
    Date date1 = dateFormat.parse(inputString1);

    Lesson lesson1 = new Lesson();
    lesson1.setTitle(title1);
    lesson1.setDeliveredOn(date1);
    repository.save(lesson1);

    String title2 = "Endpoints2";

    String inputString2 = "2017-05-03";
    Date date2 = dateFormat.parse(inputString2);

    Lesson lesson2 = new Lesson();
    lesson2.setTitle(title2);
    lesson2.setDeliveredOn(date2);
    repository.save(lesson2);

    String title3 = "Endpoints3";

    String inputString3 = "2017-04-03";
    Date date3 = dateFormat.parse(inputString3);

    Lesson lesson3 = new Lesson();
    lesson3.setTitle(title3);
    lesson3.setDeliveredOn(date3);
    repository.save(lesson3);


    MockHttpServletRequestBuilder request = get("/lessons/between?date1=2017-05-01&date2=2017-05-31")
        .contentType(MediaType.APPLICATION_JSON);

    this.mvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(2)))
        .andExpect(jsonPath("$[0].id", equalTo(lesson1.getId().intValue())))
        .andExpect(jsonPath("$[0].title", equalTo(lesson1.getTitle())))
        .andExpect(jsonPath("$[0].deliveredOn", equalTo(inputString1)))
        .andExpect(jsonPath("$[1].id", equalTo(lesson2.getId().intValue())))
        .andExpect(jsonPath("$[1].title", equalTo(lesson2.getTitle())))
        .andExpect(jsonPath("$[1].deliveredOn", equalTo(inputString2)));
  }
}