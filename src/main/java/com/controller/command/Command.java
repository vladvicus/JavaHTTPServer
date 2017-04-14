package com.controller.command;

import com.bean.Request;
import com.bean.Response;

/**
 * Created by Администратор on 11.04.2017.
 */
public interface Command {
    public void execute(Request req, Response res);
}
