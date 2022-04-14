package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
class StudentsTest {
    @Autowired
    private Students csvStudents;

    @BeforeEach
    void setUp() {
    }

    @Test
    void load() {
        assertDoesNotThrow(() -> csvStudents.load());
    }

    @Test
    void load_resultIsNotEmpty() {
        csvStudents.load();
        Collection<Student> result = csvStudents.findAll();
        assertThat(result)
            .isNotEmpty();
    }

    @Test
    void findAll() {
        csvStudents.load();
        Collection<Student> result = csvStudents.findAll();

        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void merge() {
        Scores csvScores = new CsvScores();
        csvScores.load();
        Collection<Score> scores = csvScores.findAll();

        csvStudents.load();
        csvStudents.merge(scores);
        Collection<Student> result = csvStudents.findAll();

        for (Student student : result) {
            assertThat(student.getScore()).isNotNull();
        }
    }
}