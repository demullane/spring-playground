package com.example;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
  Lesson findByTitle(String title);
  List<Lesson> findByDeliveredOnBetween(Date date1, Date date2);
}