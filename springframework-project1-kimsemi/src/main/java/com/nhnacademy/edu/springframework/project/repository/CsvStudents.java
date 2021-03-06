package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * TODO 3(완료) :
 * load 를 제외한 메소드 실행시
 * 데이터 로드가 완료되지 않으면 IllegalStateException 이 발생해야 한다.
 **/

/**
 * TODO 7(완료) :
 * singleton 클래스를 만드세요.
 */
@Component
public class CsvStudents implements Students {
    private final Map<Integer, Student> students = new HashMap<>();
    private boolean loaded = false;
    Log log = LogFactory.getLog(CsvStudents.class);

    // TODO 6(완료) : student.csv 파일에서 데이터를 읽어 students 에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try (BufferedReader studentFileReader = new BufferedReader
            (new InputStreamReader(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("data/student.csv"))))) {
            String l;
            while ((l = studentFileReader.readLine()) != null) {
                int commaIndex = l.indexOf(",");
                int seq = Integer.parseInt(l.substring(0, commaIndex));
                String name = l.substring(commaIndex + 1);
                Student student = new Student(seq, name);
                students.put(seq, student);
            }
            setLoaded(true);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Collection<Student> findAll() {
        if (!isLoaded()) {
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
        if (!isLoaded()) {
            throw new IllegalStateException("데이터 로드가 완료되지 않았습니다.");
        }
        for (Score score : scores) {
            Student student = students.get(score.getStudentSeq());
            student.setScore(score);
        }
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }
}
