package com.parser;

import com.bean.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Администратор on 10.04.2017.
 */
public class ParsingJsonImpl implements ParsingJSON {

    public Object getObjectFromJson(String body, Book book) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(body, book.getClass());
    }

    public String saveObjectTOJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {

            return mapper.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
