package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultGradeQueryService implements GradeQueryService {
    @Override
    public List<Score> getScoreByStudentName(String name) {
        Students studentRepository = CsvStudents.getInstance();
        Collection<Student> students = studentRepository.findAll();

        // TODO 5(완료): 학생 이름으로 점수를 반환합니다. 동명이인 고려합니다.
        List<Score> scores = new ArrayList<>();
        for (Student student : students) {
            if (!student.getName().equals(name)) {
                continue;
            }
            scores.add(student.getScore());
        }
        return scores;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        Scores scoreRepository = CsvScores.getInstance();
        Collection<Score> scores = scoreRepository.findAll();
        // TODO 6(완료) : 학생 번호로 점수를 반환합니다.
        List<Score> result = scores.stream()
            .filter((score) -> score.getStudentSeq() == seq)
            .collect(Collectors.toList());
        return result.get(0);
    }
}
