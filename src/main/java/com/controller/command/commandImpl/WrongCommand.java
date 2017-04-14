package com.controller.command.commandImpl;

import com.bean.Request;
import com.bean.Response;
import com.controller.command.Command;
import com.controller.command.CommandName;

/**
 * Created by Администратор on 11.04.2017.
 */
public class WrongCommand implements Command {

    public void execute(Request req, Response res) {
        System.out.println("your command  is wrong!!!");
        System.out.println("Available commands:");
        for(CommandName name: CommandName.values()){

            if(name.equals(CommandName.WRONG_COMMAND))
                continue;
            System.out.println(name+",parameter");
        }
    }
}
