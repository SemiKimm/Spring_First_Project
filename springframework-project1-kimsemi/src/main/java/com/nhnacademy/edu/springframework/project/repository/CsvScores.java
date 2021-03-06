package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Score;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * TODO 2 : (완료)
 * load 를 제외한 메소드 실행시
 * 데이터 로드가 완료되지 않으면 IllegalStateException 이 발생해야 한다.
 **/
@Component
public class CsvScores implements Scores {
    private final List<Score> scores = new ArrayList<>();
    private boolean loaded = false;
    Log log = LogFactory.getLog(CsvScores.class);

    // TODO 5(완료) : score.csv 파일에서 데이터를 읽어 scores 에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try (BufferedReader scoreFileReader = new BufferedReader
            (new InputStreamReader(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("data/score.csv"))))) {
            String l;
            while ((l = scoreFileReader.readLine()) != null) {
                int commaIndex = l.indexOf(",");
                int studentSeq = Integer.parseInt(l.substring(0, commaIndex));
                int score = Integer.parseInt(l.substring(commaIndex + 1));
                Score studentScore = new Score(studentSeq, score);
                this.scores.add(studentScore);
            }
            setLoaded(true);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Score> findAll() {
        if (!isLoaded()) {
            throw new IllegalStateException("데이터 로드가 완료되지 않았습니다.");
        }
        return this.scores;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }
}
