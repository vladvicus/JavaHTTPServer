package com.parser;

import com.bean.Book;

import java.io.IOException;

/**
 * Created by Администратор on 10.04.2017.
 */
public interface ParsingJSON {
    Object getObjectFromJson(String body, Book book) throws IOException;
    String saveObjectTOJson(Object o) throws IOException;
}
