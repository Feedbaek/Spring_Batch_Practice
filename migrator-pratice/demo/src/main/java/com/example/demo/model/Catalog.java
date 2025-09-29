package com.example.demo.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Catalog {

    @JacksonXmlElementWrapper(useWrapping = false) // <book>가 바로 반복
    @JacksonXmlProperty(localName = "book")
    private List<Book> books;


    @Setter
    @Getter
    public static class Book {

        @JacksonXmlProperty(isAttribute = true, localName = "id") // <book id="...">
        private String id;

        private String title;

        private Price price;

        @JacksonXmlElementWrapper(localName = "tags")              // <tags>...</tags>
        @JacksonXmlProperty(localName = "tag")                     // <tag>...</tag>
        private List<String> tags;

    }

    @Setter
    @Getter
    public static class Price {

        @JacksonXmlProperty(isAttribute = true, localName = "currency") // <price currency="USD">
        private String currency;

        @JacksonXmlText // <price>45.0</price> 의 텍스트 값
        private double value;

    }
}