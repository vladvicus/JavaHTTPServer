package com.controller;

import com.bean.Request;
import com.bean.Response;
import com.controller.command.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Controller {

    private final CommandProvider provider = new CommandProvider();
    private final String paramDelimeter = ",";
    private int port;
private String command;

    public Controller(int port,String command)  {
        this.command=command;
        this.port = port;
        try {
            begin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Command executeTask(String request) throws NoSuchFieldException {

        String commandName = "";
        if (request.contains(paramDelimeter)) {
            commandName = request.substring(0, request.indexOf(paramDelimeter));
        } else {
            request = request + ",";
            commandName = request.substring(0, request.indexOf(paramDelimeter));

        }
        Command executionCommand = provider.getCommand(commandName);




        return executionCommand;//.execute(rq,rp);
    }




    public void begin() throws IOException {

        ServerSocket ss = new ServerSocket(port);
        System.out.println("Waiting for request...");
        while (true) {
            Socket s = ss.accept();


            System.err.println("Client accepted on port:"+s.getPort());
            new Thread(new SocketProcessor(s)).start();
        }
    }

    private  class SocketProcessor implements Runnable{


        private  Request rq;
        private Response rp;
        private Socket socket;

        public SocketProcessor(Socket socket) {

            this.socket = socket;
        }


        public void run() {
            try {

                rq = new Request(new BufferedReader(new InputStreamReader(socket.getInputStream())));
                rq.setAccept("application/xml");
                System.out.println(rq.toString());
                rp = new Response(socket.getOutputStream());

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                executeTask(command).execute(rq,rp);
                System.out.println(rp.toString());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
