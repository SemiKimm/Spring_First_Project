package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.service.StudentService;
import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(Main.class);
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            SpringConfig.class)) {
            DataLoadService dataLoadService =
                context.getBean("csvDataLoadService", DataLoadService.class);
            dataLoadService.loadAndMerge();

            StudentService studentService = context.getBean("defaultStudentService",
                StudentService.class);
            Collection<Student> passedStudents = studentService.getPassedStudents();
            log.info(passedStudents);
            Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
            log.info(orderedStudents);

            GradeQueryService queryService = context.getBean("defaultGradeQueryService", GradeQueryService.class);
            Collection<Score> scores = queryService.getScoreByStudentName("A");
            log.info(scores);
            Score score = queryService.getScoreByStudentSeq(5);
            log.info(score);
        }
    }
}
