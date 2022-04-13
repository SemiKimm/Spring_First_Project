package com.nhnacademy.edu.springframework.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.domain.Score;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentServiceTest {
    StudentService defaultStudentService;

    @BeforeEach
    void setUp() {
        defaultStudentService = new DefaultStudentService();
    }

    @Test
    void getPassedStudents() {
//        DataLoadService dataLoadService = new CsvDataLoadService();
//        dataLoadService.loadAndMerge();
//
//        Score passedScore = new Score(0, 60);
//        Collection<Student> result = defaultStudentService.getPassedStudents();
//        for (Student student : result) {
//            assertThat(student.getScore().getScore()>=passedScore.getScore()).isTrue();
//        }
    }

    @Test
    void getStudentsOrderByScore() {
//        DataLoadService dataLoadService = new CsvDataLoadService();
//        dataLoadService.loadAndMerge();
//
//        Collection<Student> result = defaultStudentService.getStudentsOrderByScore();
//        Student[] prevStudent = {result.stream().findFirst().get()};
//        result.forEach(student -> {
//            assertThat(prevStudent[0].getScore().getScore()>=student.getScore().getScore()).isTrue();
//            prevStudent[0] = student;
//        });
    }
}