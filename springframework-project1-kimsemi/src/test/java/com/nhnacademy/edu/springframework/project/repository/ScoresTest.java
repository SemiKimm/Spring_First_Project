package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import com.nhnacademy.edu.springframework.project.domain.Score;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
class ScoresTest {
    @Autowired
    Scores csvScores;

    @BeforeEach
    void setUp() {
    }

    @Test
    void load() {
        assertDoesNotThrow(() -> csvScores.load());
    }

    @Test
    void load_resultIsNotEmpty() {
        csvScores.load();
        List<Score> result = csvScores.findAll();
        assertThat(result)
            .isNotEmpty();
    }

    @Test
    void load_loadedIsTrue() {
        csvScores.load();
        assertThat(csvScores.isLoaded()).isTrue();
    }

    @Test
    void findAll() {
        csvScores.load();
        List<Score> result = csvScores.findAll();

        assertThat(result.size()).isEqualTo(3);
    }
}