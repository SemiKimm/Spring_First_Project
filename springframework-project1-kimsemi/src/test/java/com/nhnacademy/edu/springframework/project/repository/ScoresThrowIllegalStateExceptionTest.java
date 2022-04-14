package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ScoresThrowIllegalStateExceptionTest {
    @Autowired
    Scores csvScores;

    @DisplayName("load()가 완료되지 않은경우 IllegalStateException 이 발생한다.")
    @Test
    void findAll_loadIsNotComplete_throwIllegalStateException(){
        assertThatThrownBy(()->csvScores.findAll())
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContainingAll("데이터 로드");
    }
}
