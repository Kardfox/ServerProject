package com.main.client;

//io
import java.io.IOException;
import java.util.Scanner;


class Connect {
    private Client client;

    public Connect() {
        try {
            start();
            client.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public void start() throws IOException {
        Scanner scan = new Scanner(System.in);

        client = new Client("localhost", 5000, "George");

        while (true) {
            if (getUserMessage(scan).endsWith("Endconn~")) {
                scan.close();
                return;
            }

            if (getServerMessage().endsWith("Endconn~")) {
                scan.close();
                return;
            }
        }
    }

    private String getUserMessage(Scanner scan) throws IOException {
        String message;

        while (true) {
            System.out.print("[you]: ");
            message = scan.nextLine();
            client.sendMessage(message);
            if (message.endsWith("~") || message == null) break;
        }

        return message;
    }

    private String getServerMessage() throws IOException {
        String message;

        while (true) {
            System.out.print("[server]: ");
            message = client.reciveMessage();
            System.out.print(message + "\n");
            if (message.endsWith("~") || message == null) break;
        }

        return message;
    }

}

public class Main {
    public static void main(String[] args) {
        new Connect();
    }
}