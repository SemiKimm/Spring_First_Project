package com.nhnacademy.edu.springframework.project.repository;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {
    Students csvStudents;
    @BeforeEach
    void setUp() {
        csvStudents = CsvStudents.getInstance();
    }

    @Test
    void load() {
        assertDoesNotThrow(()->csvStudents.load());
    }

    @DisplayName("load() 했을때 csvStudents.findAll() 해서 나온 result는 비어있지 않다.")
    @Test
    void load_resultIsNotEmpty(){
        csvStudents.load();
        Collection<Student> result = csvStudents.findAll();
        assertThat(result)
            .isNotEmpty();
    }

    @Test
    void findAll() {
        int size = 0;
        try (BufferedReader studentFileReader = new BufferedReader
            (new InputStreamReader(Objects.requireNonNull(
                Test.class.getClassLoader().getResourceAsStream("data/student.csv"))))) {
            while (studentFileReader.readLine() != null) {
                size++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        csvStudents.load();
        Collection<Student> result = csvStudents.findAll();

        assertThat(result.size()).isEqualTo(size);
    }

    @Test
    void merge() {
        Scores csvScores = CsvScores.getInstance();
        csvScores.load();
        Collection<Score> scores = csvScores.findAll();

        csvStudents.load();
        csvStudents.merge(scores);
        Collection<Student> result = csvStudents.findAll();

        for(Student student:result){
            assertThat(student.getScore()).isNotNull();
        }
    }
}