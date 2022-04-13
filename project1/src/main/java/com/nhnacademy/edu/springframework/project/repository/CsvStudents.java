package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


/** TODO 3(완료&검토요망) - load() 가 되는중에 다른 메소드가 실행되는 경우의 예외 발생 처리를 해결하지 못했습니다..
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
        try (FileInputStream studentFileIn = new FileInputStream(
            "D:\\NHN_Academy\\lectureSpring\\day2\\project1\\src\\main\\resources\\data\\student.csv")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(studentFileIn));
            String l;
            while((l=br.readLine())!=null){
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
