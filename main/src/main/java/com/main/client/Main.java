package com.main.client;

//io
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            Client connect = new Client("localhost", 5000);
            connect.sendMessage("George");
            connect.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }
}