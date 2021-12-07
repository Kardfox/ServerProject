package com.main.client;

//io
import java.io.IOException;
import java.util.Scanner;


class Connect {
    private Client client;

    public Connect(String userName) {
        try {
            start(userName);
            client.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public void start(String userName) throws IOException {
        try (Scanner scan = new Scanner(System.in))  {
            client = new Client("localhost", 5000, userName);

            while (true) {
                if (getUserMessage(scan).endsWith("Endconn~")) {
                    return;
                }

                if (getServerMessage().endsWith("Endconn~")) {
                    return;
                }
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
        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("\nEnter your name: ");
            new Connect(scan.nextLine());
        }
    }
}