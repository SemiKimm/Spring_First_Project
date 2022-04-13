package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    @Override
    public Collection<Student> getPassedStudents() {
        Students studentRepository = CsvStudents.getInstance();
        Collection<Student> students = studentRepository.findAll();
        // TODO 1(완료) : pass 한 학생만 반환하도록 수정하세요.
        return students.stream()
            .filter((student) -> !student.getScore().isFail())
            .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        Students studentRepository = CsvStudents.getInstance();
        Collection<Student> students = studentRepository.findAll();
        // TODO 4(완료) : 성적 순으로 학생 정보를 반환합니다.
        return students.stream()
            .sorted((student1, student2) ->
                student2.getScore().getScore() - student1.getScore().getScore())
            .collect(Collectors.toList());
    }
}
