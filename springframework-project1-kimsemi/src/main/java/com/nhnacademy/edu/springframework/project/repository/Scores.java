package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Score;
import java.util.List;

public interface Scores {
    void load();

    List<Score> findAll();
}
