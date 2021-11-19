package com.main.client;

//io
import java.io.IOException;

//net
import java.net.Socket;

import com.main.server.ServerData;


public class Client extends Socket {
    private final ServerData server;

    public Client(String host, int port) throws IOException {
        super(host, port);

        server = new ServerData(getInputStream(), getOutputStream());
    }

    public String reciveMessage() {
        StringBuffer fullMessage = new StringBuffer();
        String message;

        try {
            while ((message = server.inReader.readLine()) != null) {
                fullMessage.append(message);
            }
        } catch (IOException erorr) {
            System.out.println(erorr.getMessage());
            System.exit(20);
        }

        return fullMessage.toString();
    }

    public void sendMessage(String message) throws IOException {
        server.outWriter.write(message);
        server.outWriter.flush();
    }
}
