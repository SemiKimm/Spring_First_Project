package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.annotation.ScoresCsv;
import com.nhnacademy.edu.springframework.project.annotation.StudentsCsv;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultGradeQueryService implements GradeQueryService {
    private final Students studentRepository;
    private final Scores scoreRepository;

    @Autowired
    public DefaultGradeQueryService(
        @StudentsCsv Students studentRepository, @ScoresCsv Scores scoreRepository) {
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
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
        Collection<Score> scores = scoreRepository.findAll();
        // TODO 6(완료) : 학생 번호로 점수를 반환합니다.
        return scores.stream()
            .filter((score) -> score.getStudentSeq() == seq)
            .findFirst().orElse(null);
    }
}
