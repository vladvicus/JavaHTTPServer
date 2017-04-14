package com.controller.command.commandImpl;

import com.bean.Book;
import com.bean.Request;
import com.bean.Response;
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

/**
 * Created by Администратор on 11.04.2017.
 */
public class GetBook implements Command {


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


        rp.setVersion(rq.getHttpVer());
        System.out.println(rp.getVersion());
        rp.setStatusCode(Config.s_code_200());
        rp.setContentType(rq.getAccept());
        System.out.println("Book-->" + rq.getBook_id());

        Book book = new Book();
        int counter = 0;
        for (Book singleBook : DataStore.getAllBook())
            if (singleBook.getId() == rq.getBook_id()) {
                book = singleBook;
                counter++;
            }


        if (acceptType.equals(Config.accept_type_xml()) && counter != 0) {

            StringWriter writer = new StringWriter();
            ParsingJAXB parserJaxb = new ParsingJaxbImpl();
            parserJaxb.saveObject(writer, book);

            body = writer.toString();

            rp.setContentLength(String.valueOf(body.getBytes().length));
            rp.setBody(body);

        } else if (acceptType.equals(Config.accept_type_json()) && counter != 0) {
            ParsingJSON parserJson = new ParsingJsonImpl();
            try {
                body = parserJson.saveObjectTOJson(book);
            } catch (IOException e) {
                e.printStackTrace();
            }
            rp.setContentLength(String.valueOf(body.getBytes().length));
            rp.setBody(body);
        } else {

            rp.setStatusCode(Config.s_code_404());
            rp.setBody("No Such ID!!!");
            rp.setContentLength(String.valueOf(rp.getBody().getBytes().length));
        }

        try {
            rp.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
