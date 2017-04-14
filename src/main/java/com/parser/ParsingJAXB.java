package com.parser;

import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Администратор on 08.04.2017.
 */
public interface ParsingJAXB {
    Object getObject(StringReader body, Class c) throws JAXBException;
    void saveObject(StringWriter writer, Object o) throws JAXBException;
}
