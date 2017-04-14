package com.controller.command.commandImpl;

import com.bean.*;
import com.controller.command.Command;
import com.helper.Config;
import com.parser.ParsingJAXB;
import com.parser.ParsingJSON;
import com.parser.ParsingJaxbImpl;
import com.parser.ParsingJsonImpl;
import com.utils.DataStore;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by Администратор on 11.04.2017.
 */
public class GetAllBooks implements Command {

    static final String HTML_START =
            "<html>" +
                    "<title>HTTP Server in java</title>" +
                    "<body>";

    static final String HTML_END =
            "</body>" +
                    "</html>";
    public void execute(Request req, Response res) {

        String acceptType = req.getAccept();

        try {
            fillBody(req, res, acceptType);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    private void fillBody(Request rq, Response rp, String acceptType) throws JAXBException {
        String body = "";
        List<Book> books = DataStore.getAllBook();


        rp.setVersion(rq.getHttpVer());
        System.out.println(rp.getVersion());
        rp.setStatusCode(Config.s_code_200());
        rp.setContentType(rq.getAccept());



        Books book = new Books(books);


        if (acceptType.equals(Config.accept_type_xml())) {

            StringWriter writer = new StringWriter();
            ParsingJAXB parserJaxb = new ParsingJaxbImpl();
            parserJaxb.saveObject(writer, book);

            body = writer.toString();

            rp.setContentLength(String.valueOf(body.getBytes().length));
            rp.setBody(body);

        } else {
            ParsingJSON parserJson = new ParsingJsonImpl();
            try {
                body = parserJson.saveObjectTOJson(book);
            } catch (IOException e) {
                e.printStackTrace();
            }
            rp.setContentLength(String.valueOf(body.getBytes().length));
            rp.setBody(body);
        }

        try {
            rp.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

