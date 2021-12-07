package com.main.server;

//io
import java.io.IOException;

//util
import java.util.Scanner;


class Connect {
    private Server server;

    public Connect() {
        try {
            start();
            server.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public void start() throws IOException {
        try (Scanner scan = new Scanner(System.in)) {
            server = new Server("localhost", 5000);
            
            while (true) {
                if (getClientMessage().endsWith("Endconn~")) {
                    return;
                }

                if (getUserMessage(scan).endsWith("Endconn~")) {
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
            server.sendMessage(message);
            if (message.endsWith("~") || message == "connect lose") break;
        }

        return message;
    }

    private String getClientMessage() throws IOException {
        String message;

        while (true) {
            System.out.printf("[%s]: ", server.getClientName());
            message = server.reciveMessage();
            System.out.print(message + "\n");
            if (message.endsWith("~") || message == "connect lose") break;
        }

        return message;
    }

}

public class Main {
    public static void main(String[] args) {
        new Connect();
    }
}
