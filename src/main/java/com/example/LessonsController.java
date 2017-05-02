package com.example;

import org.springframework.web.bind.annotation.*;

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

}
