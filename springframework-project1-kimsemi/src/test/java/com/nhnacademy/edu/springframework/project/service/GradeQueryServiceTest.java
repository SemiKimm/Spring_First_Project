package com.nhnacademy.edu.springframework.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
class GradeQueryServiceTest {
    @Autowired
    GradeQueryService defaultGradeQueryService;
    @Autowired
    DataLoadService dataLoadService;

    @BeforeEach
    void setUp() {
        dataLoadService.loadAndMerge();
    }

    @Test
    void getScoreByStudentName() {
        Map<Integer, Score> scores = getScoresFromTestCsv();

        String name = "A";
        List<Score> result = defaultGradeQueryService.getScoreByStudentName(name);

        result.forEach(score -> {
            assertThat(scores.get(score.getStudentSeq()))
                .isNotNull();
            assertThat(scores.get(score.getStudentSeq()).equals(score)).isTrue();
        });
    }

    @Test
    void getScoreByStudentSeq() {
        Map<Integer, Score> scores = getScoresFromTestCsv();

        int seq = 1;
        Score result = defaultGradeQueryService.getScoreByStudentSeq(seq);

        assertThat(result.equals(scores.get(result.getStudentSeq()))).isTrue();
    }

    Map<Integer,Score> getScoresFromTestCsv(){
        String path = "src/test/resources/data/score.csv";
        File file = new File(path);
        Map<Integer, Score> scores = new HashMap<>();
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedReader bufferedReader
                 = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String l;
            int commaIndex;
            while ((l = bufferedReader.readLine()) != null) {
                commaIndex = l.indexOf(",");
                scores.put(Integer.parseInt(l.substring(0, commaIndex)),
                    new Score(Integer.parseInt(l.substring(0, commaIndex)),
                        Integer.parseInt(l.substring(commaIndex + 1))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }
}