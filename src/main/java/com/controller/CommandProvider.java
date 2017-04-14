package com.controller;

import com.controller.command.Command;
import com.controller.command.CommandName;
import com.controller.command.commandImpl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Администратор on 11.04.2017.
 */
public class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {

        repository.put(CommandName.GET_ALL_BOOKS, new GetAllBooks());
        repository.put(CommandName.GET_BOOK, new GetBook());
        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.DELETE_BOOK, new DeleteBook());
        repository.put(CommandName.WRONG_COMMAND, new WrongCommand());

        // ...
    }

    public Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            // write log
            command = repository.get(CommandName.WRONG_COMMAND);
        }
        return command;
    }

    public boolean checkCommand(String name) {
        CommandName[] comm = CommandName.values();
        for (CommandName command : comm) {
            if (command.equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
