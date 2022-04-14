package com.nhnacademy.edu.springframework.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
class StudentServiceTest {
    @Autowired
    StudentService defaultStudentService;
    @Autowired
    DataLoadService dataLoadService;

    @BeforeEach
    void setUp() {
        dataLoadService.loadAndMerge();
    }

    @Test
    void getPassedStudents() {
        Score passedScore = new Score(0, 60);
        Collection<Student> result = defaultStudentService.getPassedStudents();
        for (Student student : result) {
            assertThat(student.getScore().getScore()>=passedScore.getScore()).isTrue();
        }
    }

    @Test
    void getStudentsOrderByScore() {
        Collection<Student> result = defaultStudentService.getStudentsOrderByScore();
        Student[] prevStudent = {result.stream().findFirst().get()};
        result.forEach(student -> {
            assertThat(prevStudent[0].getScore().getScore()>=student.getScore().getScore()).isTrue();
            prevStudent[0] = student;
        });
    }
}