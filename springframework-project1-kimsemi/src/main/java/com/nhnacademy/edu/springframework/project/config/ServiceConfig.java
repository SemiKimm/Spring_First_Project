package com.nhnacademy.edu.springframework.project.config;

import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultGradeQueryService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import com.nhnacademy.edu.springframework.project.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.nhnacademy.edu.springframework")
@EnableAspectJAutoProxy
public class ServiceConfig {
    @Bean
    public DataLoadService csvDataLoadService(Scores scores, Students students){
        return new CsvDataLoadService(scores, students);
    }

    @Bean
    public StudentService defaultStudentService(Students students){
        return new DefaultStudentService(students);
    }

    @Bean
    public GradeQueryService defaultGradeQueryService(Students students, Scores scores){
        return new DefaultGradeQueryService(students, scores);
    }
}
