package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    @DisplayName("load() 한 후에 findAll()을 통해 불러온 List<Score> result는 비어있으면 안된다.")
    @Test
    void load() {
        csvScores.load();
        List<Score> result = csvScores.findAll();
        assertThat(result)
            .isNotEmpty();
    }

    @Test
    void findAll() {
        int size = 0;
        try (BufferedReader scoreFileReader = new BufferedReader
            (new InputStreamReader(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("data/score.csv"))))) {
            String l;
            while ((l = scoreFileReader.readLine()) != null) {
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