package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import com.nhnacademy.edu.springframework.project.domain.Score;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
public class StudentsThrowIllegalStateExceptionTest {
    @Autowired
    private Students csvStudents;

    @Autowired
    private Scores csvScores;

    @DisplayName("load()가 완료되지 않은경우에 findAll을 호출하면 IllegalStateException 이 발생한다.")
    @Test
    void findAll_loadIsNotComplete_throwIllegalStateException(){
        assertThatThrownBy(()->csvStudents.findAll())
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContainingAll("데이터 로드");
    }

    @DisplayName("load()가 완료되지 않은경우에 merge()를 호출하면 IllegalStateException 이 발생한다.")
    @Test
    void merge_csvStudentsLoadIsNotComplete_throwIllegalStateException(){
        csvScores.load();
        Collection<Score> scores= csvScores.findAll();

        assertThatThrownBy(()->csvStudents.merge(scores))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContainingAll("데이터 로드");
    }
}
