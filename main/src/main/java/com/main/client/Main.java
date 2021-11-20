package com.main.client;

//io
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);

            Client client = new Client("localhost", 5000, "George");

            while (true) {
                String message;
                while (true) {
                    System.out.print("[you]: ");
                    message = scan.nextLine();
                    client.sendMessage(message);
                    if (message.endsWith("~")) break;
                }

                while (true) {
                    System.out.print("[server]: ");
                    message = client.reciveMessage();
                    System.out.print(message + "\n");
                    if (message.endsWith("~")) break;
                }
    
                if (message.equals("Endconn~")) break;
            }

            client.close();
            scan.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }
}