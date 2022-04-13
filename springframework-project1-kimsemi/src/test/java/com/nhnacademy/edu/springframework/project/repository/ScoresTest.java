package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoresTest {
    Scores csvScores;

    @BeforeEach
    void setUp() {
        csvScores = CsvScores.getInstance();
    }

    @Test
    void load() {
        assertDoesNotThrow(() -> csvScores.load());
    }

    @DisplayName("load() 한 후에 findAll()을 통해 불러온 List<Score> result는 csv가 비어있지 않는한 비어있지 않아야 한다.")
    @Test
    void load_resultIsNotEmpty() {
        int size = 0;
        try (BufferedReader scoreFileReader = new BufferedReader
            (new InputStreamReader(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("data/score.csv"))))) {
            while(scoreFileReader.readLine()!=null){
                size++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvScores.load();
        List<Score> result = csvScores.findAll();
        if(size!=0){
            assertThat(result)
                .isNotEmpty();
        }
    }

    @Test
    void findAll() {
        int size = 0;
        try (BufferedReader scoreFileReader = new BufferedReader
            (new InputStreamReader(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("data/score.csv"))))) {
            while (scoreFileReader.readLine() != null) {
                size++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        csvScores.load();
        List<Score> result = csvScores.findAll();

        assertThat(result.size()).isEqualTo(size);
    }
}