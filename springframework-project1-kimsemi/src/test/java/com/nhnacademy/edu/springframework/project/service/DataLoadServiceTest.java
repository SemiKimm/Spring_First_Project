package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
class DataLoadServiceTest {
    @Autowired
    DataLoadService csvDataLoadService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void loadAndMerge() {
        assertDoesNotThrow(()->csvDataLoadService.loadAndMerge());
    }
}