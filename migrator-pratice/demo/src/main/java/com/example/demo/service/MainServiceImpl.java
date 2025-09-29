package com.example.demo.service;

import com.example.demo.model.Catalog;
import com.example.demo.reader.MigrationXmlReaderImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Log4j2
@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final ExecutorService fixedExecutor;
    private final MigrationXmlReaderImpl migrationXmlReaderImpl;
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Override
    public void execute(String input, String output) {
        // 비즈니스 로직 구현
        log.info("Input: {}", input);
        log.info("Output: {}", output);

        Future<Catalog> ret = fixedExecutor.submit(() -> migrationXmlReaderImpl.readFromClasspath("catalog.xml"));
        log.info("Task submitted, doing other work...");

        try {
            Catalog catalog = ret.get(); // Future에서 결과를 가져옴
            log.info("Catalog read: {}", objectMapper.writeValueAsString(catalog));
        } catch (Exception e) {
            log.error("Error reading catalog", e);
        }
    }
}
