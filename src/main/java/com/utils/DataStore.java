package com.utils;

import com.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 11.04.2017.
 */
public class DataStore {
    static List<Book> books = new ArrayList<Book>();

    public static List<Book> getBooks() {
        return books;
    }

    public static void setBooks(List<Book> books) {
        DataStore.books = books;
    }

    public static List<Book> getAllBook() {

        books.add( new Book(1, "Thinking in Java", "B.Ekkel", 1000, "Eng"));
      books.add(new Book(2, "Thinking in Java", "B.Ekkel", 500, "Rus"));

        return books;
    }

    public static void addBook(Book book) {

        books.add(book);
    }
}