package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.annotation.StudentsCsv;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {
    private final Students studentRepository;

    @Autowired
    public DefaultStudentService(
        @StudentsCsv Students studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Collection<Student> getPassedStudents() {
        Collection<Student> students = studentRepository.findAll();
        // TODO 1(완료) : pass 한 학생만 반환하도록 수정하세요.
        return students.stream()
            .filter((student) -> !student.getScore().isFail())
            .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        Collection<Student> students = studentRepository.findAll();
        // TODO 4(완료) : 성적 순으로 학생 정보를 반환합니다.
        return students.stream()
            .sorted((student1, student2) ->
                student2.getScore().getScore() - student1.getScore().getScore())
            .collect(Collectors.toList());
    }
}
