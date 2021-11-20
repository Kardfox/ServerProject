package com.main.client;

//io
import java.io.IOException;

//net
import java.net.Socket;


public class Client extends Socket {
    private final ServerData server;

    public Client(String host, int port, String name) throws IOException {
        super(host, port);

        server = new ServerData(getInputStream(), getOutputStream());
        sendMessage(name);
    }

    public String reciveMessage() throws IOException {
        return server.inReader.readLine();
    }

    public void sendMessage(String message) throws IOException {
        server.outWriter.write(message + "\n");
        server.outWriter.flush();
    }
}
