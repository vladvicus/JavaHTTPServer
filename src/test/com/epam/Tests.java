package test.com.epam;

import main.java.com.epam.bean.Book;
import main.java.com.epam.parser.Parsing;
import main.java.com.epam.parser.ParsingImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;





public class Tests {
    private Parsing parser;
    private File file;

    @BeforeTest
    public void setUp() throws Exception {
        parser = new ParsingImpl();

        file = new File("book.xml");
        file1= new File("book.json");
    }


    @Test
    public void testGetObject() throws Exception {
    Book book = (Book)parser.getObject(file, Book.class);
        System.out.println(book);
    }


    @Test
    public void testSaveObject() throws Exception {
        Book book = new Book(1,"Thinking in Java", "B.Ekkel" ,1000, "Eng" );
        Book book1 = new Book(2,"Thinking in Java", "B.Ekkel" ,500, "Rus" );

        parser.saveObject(file,book);
        parser.saveObject(file,book1);
    }
}
