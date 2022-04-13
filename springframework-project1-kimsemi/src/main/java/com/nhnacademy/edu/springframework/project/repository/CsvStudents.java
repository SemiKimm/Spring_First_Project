package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/** TODO 3(완료) :
 * load 를 제외한 메소드 실행시
 * 데이터 로드가 완료되지 않으면 IllegalStateException 이 발생해야 한다.
 **/

/**
 * TODO 7(완료) :
 * singleton 클래스를 만드세요.
 */
public class CsvStudents implements Students {
    private static final Students INSTANCE = new CsvStudents();

    private final Map<Integer,Student> students = new HashMap<>();

    public static Students getInstance() {
        return INSTANCE;
    }

    // TODO 6(완료) : student.csv 파일에서 데이터를 읽어 students 에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try (BufferedReader studentFileReader = new BufferedReader
            (new InputStreamReader(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("data/student.csv"))))) {
            String l;
            while((l=studentFileReader.readLine())!=null){
                int commaIndex = l.indexOf(",");
                int seq = Integer.parseInt(l.substring(0,commaIndex));
                String name = l.substring(commaIndex+1);
                Student student = new Student(seq,name);
                students.put(seq,student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Student> findAll() {
        if(students.isEmpty()){
            throw new IllegalStateException("데이터 로드가 완료되지 않았습니다.");
        }
        return this.students.values();
    }

    /**
     * TODO 8(완료) : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        if(students.isEmpty()){
            throw new IllegalStateException("데이터 로드가 완료되지 않았습니다.");
        }
        for(Score score:scores){
            Student student = students.get(score.getStudentSeq());
            student.setScore(score);
        }
    }
}
