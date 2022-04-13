package com.nhnacademy.edu.springframework.project.config;

import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultGradeQueryService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import com.nhnacademy.edu.springframework.project.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public DataLoadService csvDataLoadService(){
        return new CsvDataLoadService();
    }

    @Bean
    public StudentService defaultStudentService(){
        return new DefaultStudentService();
    }

    @Bean
    public GradeQueryService defaultGradeQueryService(){
        return new DefaultGradeQueryService();
    }
}
