package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import com.nhnacademy.edu.springframework.project.service.Student;
import com.nhnacademy.edu.springframework.project.service.StudentService;
import java.util.Collection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigMain {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            "com.nhnacademy.edu.springframework")) {
            DataLoadService dataLoadService =
                context.getBean("csvDataLoadService", DataLoadService.class);
            dataLoadService.loadAndMerge();

            StudentService studentService = context.getBean("defaultStudentService",
                StudentService.class);
            Collection<Student> passedStudents = studentService.getPassedStudents();
            System.out.println(passedStudents);
            Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
            System.out.println(orderedStudents);

            GradeQueryService queryService = context.getBean("defaultGradeQueryService", GradeQueryService.class);
            Collection<Score> scores = queryService.getScoreByStudentName("A");
            System.out.println(scores);
            Score score = queryService.getScoreByStudentSeq(5);
            System.out.println(score);
        }
    }
}
