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
        Map<Integer, Score> scores = new HashMap<>();
        Score score1 = new Score(1, 30);
        Score score2 = new Score(2, 80);
        Score score3 = new Score(3, 70);
        scores.put(1,score1);
        scores.put(2,score2);
        scores.put(3,score3);

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
        Map<Integer, Score> scores = new HashMap<>();
        Score score1 = new Score(1, 30);
        Score score2 = new Score(2, 80);
        Score score3 = new Score(3, 70);
        scores.put(1,score1);
        scores.put(2,score2);
        scores.put(3,score3);

        int seq = 1;
        Score result = defaultGradeQueryService.getScoreByStudentSeq(seq);

        assertThat(result.equals(scores.get(result.getStudentSeq()))).isTrue();
    }
}