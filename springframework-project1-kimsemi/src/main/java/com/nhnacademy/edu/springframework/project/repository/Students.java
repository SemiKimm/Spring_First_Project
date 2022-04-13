package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;

import java.util.Collection;

public interface Students {
    void load();

    Collection<Student> findAll();

    void merge(Collection<Score> scores);
}
