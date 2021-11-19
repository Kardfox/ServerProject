package com.main.server;

//io
import java.io.IOException;

//util
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server("localhost", 5000);

            StringBuilder message = new StringBuilder();


            server.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }
}
