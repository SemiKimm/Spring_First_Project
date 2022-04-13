package com.nhnacademy.edu.springframework.project.config;

import com.nhnacademy.edu.springframework.project.annotation.ScoresCsv;
import com.nhnacademy.edu.springframework.project.annotation.StudentsCsv;
import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    @ScoresCsv
    public Scores csvScores(){
        return CsvScores.getInstance();
    }

    @Bean
    @StudentsCsv
    public Students csvStudents(){
        return CsvStudents.getInstance();
    }
}
