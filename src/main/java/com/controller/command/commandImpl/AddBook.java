package com.controller.command.commandImpl;

import com.bean.Book;
import com.bean.Request;
import com.bean.Response;
import com.controller.command.Command;
import com.helper.Config;
import com.parser.JsonUtils;
import com.parser.marshaller.MarshallerHelper;
import com.utils.DataStore;

import java.io.IOException;

/**
 * Created by Администратор on 11.04.2017.
 */
public class AddBook implements Command {

    public void execute(Request req, Response res) {

        boolean flag = true;
        res.setVersion(req.getHttpVer());
        System.out.println("!!!!!"+req.getAccept());
        System.out.println("!1111111"+req.getBody());
        Book bookCreate = null;
        String body = "";
        try {
            if (req.getAccept().equals(Config.accept_type_xml())) {

                bookCreate =(Book)(MarshallerHelper.unmarshall(req.getBody(), Book.class));
                System.out.println("!!!!!!!!!!!" + bookCreate.toString());

                res.setContentLength(String.valueOf(body.getBytes().length));
                res.setContentType(req.getContentType());
                res.setStatusCode(Config.s_code_201());
res.setBody(req.getBody());
            } else if (req.getAccept().equals(Config.accept_type_json())) {

                bookCreate = JsonUtils.fromJson(req.getBody(), Book.class);

                res.setContentLength(String.valueOf(body.getBytes().length));
                res.setStatusCode(Config.s_code_201());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            res.setStatusCode(Config.s_code_400());
            flag = false;
        }

        if (flag) {
            DataStore.getAllBook();
            DataStore.addBook(bookCreate);
            System.out.println(DataStore.getBooks());
        }
        try {
            res.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

