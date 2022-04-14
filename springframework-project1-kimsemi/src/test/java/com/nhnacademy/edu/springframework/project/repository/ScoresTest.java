package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void load_loadedIsTrue(){
        csvScores.load();
        assertThat(csvScores.isLoaded()).isTrue();
    }

    @Test
    void findAll() {
        int size = 0;
        String path = "src/test/resources/data/score.csv";
        File file = new File(path);
        Map<Integer, Score> scores = new HashMap<>();
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedReader bufferedReader
                 = new BufferedReader(new InputStreamReader(fileInputStream))) {
            while (bufferedReader.readLine() != null) {
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