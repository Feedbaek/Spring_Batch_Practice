package com.example.demo.reader;

import com.example.demo.model.Catalog;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class MigrationXmlReaderImpl implements MigrationXmlReader {

    private final XmlMapper xmlMapper = new XmlMapper();
    private final ResourceLoader resourceLoader;

    public Catalog readFromClasspath(String path) {
        log.info("Reading catalog from {}", path);
        Resource res = resourceLoader.getResource("classpath:" + path);
        try (var in = res.getInputStream()) {
            return xmlMapper.readValue(in, Catalog.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
