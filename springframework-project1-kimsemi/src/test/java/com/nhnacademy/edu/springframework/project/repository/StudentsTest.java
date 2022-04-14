package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentsTest {
    private Students csvStudents;

    private Scores csvScores;

    @BeforeEach
    void setUp() {
        csvStudents = new CsvStudents();
        csvScores = mock(CsvScores.class);
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
                getClass().getClassLoader().getResourceAsStream("data/student.csv"))))) {
            while(studentFileReader.readLine()!=null){
                size++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvStudents.load();
        Collection<Student> result = csvStudents.findAll();

        assertThat(result.size()).isEqualTo(size);
    }

    @DisplayName("load()가 완료되지 않은경우에 findAll을 호출하면 IllegalStateException 이 발생한다.")
    @Test
    void findAll_loadIsNotComplete_throwIllegalStateException(){
        assertThatThrownBy(()->csvStudents.findAll())
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContainingAll("데이터 로드");
    }


    @Test
    void merge() {
        Scores csvScores = new CsvScores();
        csvScores.load();
        Collection<Score> scores = csvScores.findAll();

        csvStudents.load();
        csvStudents.merge(scores);
        Collection<Student> result = csvStudents.findAll();

        for(Student student:result){
            assertThat(student.getScore()).isNotNull();
        }
    }

    @DisplayName("load()가 완료되지 않은경우에 merge()를 호출하면 IllegalStateException 이 발생한다.")
    @Test
    void merge_loadIsNotComplete_throwIllegalStateException(){
        List<Score> scoreList = new ArrayList<>();
        when(csvScores.findAll()).thenReturn(scoreList);
        Collection<Score> scores= csvScores.findAll();
        assertThatThrownBy(()->csvStudents.merge(scores))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContainingAll("데이터 로드");
    }
}