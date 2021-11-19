package com.main.server;

//io
import java.io.IOException;

//net
import java.net.InetAddress;

import java.net.ServerSocket;
import java.net.Socket;

import com.main.client.ClientData;

public class Server extends ServerSocket {
    private ClientData client;

    public Server(String host, int port) throws IOException {
        super(port, 1, InetAddress.getByName(host));

        System.out.println("Open a server. Waiting a connect by client");
        openConnection();
    }

    private void openConnection() throws IOException {
        Socket socketClient = accept();

        client = new ClientData(socketClient.getInputStream(), socketClient.getOutputStream());

        System.out.printf("Connect by: %s", client.clientName);
    }

    public String reciveMessage() {
        StringBuffer fullMessage = new StringBuffer();
        String message;

        try {
            while ((message = client.inReader.readLine()) != null) {
                fullMessage.append(message);
            }
        } catch (IOException erorr) {
            System.out.println(erorr.getMessage());
            System.exit(20);
        }

        return fullMessage.toString();
    }

    public void sendMessage(String message) throws IOException {
        client.outWriter.write(message);
        client.outWriter.flush();
    }
}
