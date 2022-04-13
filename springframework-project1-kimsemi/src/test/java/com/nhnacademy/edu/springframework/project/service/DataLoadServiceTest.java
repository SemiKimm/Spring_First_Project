package com.nhnacademy.edu.springframework.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {
    DataLoadService csvDataLoadService;

    @BeforeEach
    void setUp() {
//        csvDataLoadService = new CsvDataLoadService();
    }

    @Test
    void loadAndMerge() {
        assertDoesNotThrow(()->csvDataLoadService.loadAndMerge());
    }
}