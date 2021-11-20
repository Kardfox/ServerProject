package com.main.server;

//io
import java.io.IOException;

//net
import java.net.InetAddress;

import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ServerSocket {
    private ClientData client;

    public Server(String host, int port) throws IOException {
        super(port, 5, InetAddress.getByName(host));

        System.out.println("\nOpen a server. Waiting a connect by client");
        openConnection();
    }

    private void openConnection() throws IOException {
        Socket socketClient = accept();

        client = new ClientData(socketClient.getInputStream(), socketClient.getOutputStream());

        System.out.printf("Connect by %s\n", client.clientName);
    }

    public String reciveMessage() throws IOException {
        return client.inReader.readLine();
    }

    public void sendMessage(String message) throws IOException {
        client.outWriter.write(message + "\n");
        client.outWriter.flush();
    }

    public String getClientName() {
        return client.clientName;
    }

    @Override
    protected void finalize() throws Throwable {
       close();
   }
}
