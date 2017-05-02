package com.example;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

  private final LessonRepository repository;

  public LessonsController(LessonRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  public Iterable<Lesson> all() {
    return this.repository.findAll();
  }

  @PostMapping("")
  public Lesson create(@RequestBody Lesson lesson) {
    return this.repository.save(lesson);
  }

  @PatchMapping("/{id}")
  public Lesson update(@PathVariable Long id, @RequestBody Lesson updatedLesson) {
    Lesson outdatedLesson = this.repository.findOne(id);
    outdatedLesson.setTitle(updatedLesson.getTitle());
    outdatedLesson.setDeliveredOn(updatedLesson.getDeliveredOn());
    return this.repository.save(outdatedLesson);
  }

  @GetMapping("/{id}")
  public LessonResponse show(@PathVariable Long id) {
    Lesson lesson = this.repository.findOne(id);
    LessonResponse lessonResponse = new LessonResponse(lesson.getId(), lesson.getTitle());
    return lessonResponse;
  }

  public class LessonResponse {
    private final long id;
    private final String title;

    public LessonResponse(long id, String title) {
      this.id = id;
      this.title = title;
    }

    public Long getId() {
      return id;
    }

    public String getTitle() {
      return title;
    }
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    this.repository.delete(id);
  }

  @GetMapping("/find/{title}")
  public Lesson findByTitle(@PathVariable String title) {
    return this.repository.findByTitle(title);
  }

  @GetMapping("/between")
  public List<Lesson> findAllBetweenDates(
      @RequestParam("date1")
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
      @RequestParam("date2")
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
    return this.repository.findByDeliveredOnBetween(date1, date2);
  }

}
