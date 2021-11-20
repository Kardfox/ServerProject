package com.main.server;

//io
import java.io.IOException;

//util
import java.util.Scanner;


class App {
    private Server server;

    public App() {
        try {
            server = new Server("localhost", 5000);

            start();

            server.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    private void start() throws IOException {
        Scanner scan = new Scanner(System.in);

        String message = "";
        while (true) {
            while (true) {
                System.out.printf("[%s]: ", server.getClientName());
                message = server.reciveMessage();
                System.out.print(message + "\n");
                if (message.endsWith("~")) break;
            }

            if (message.endsWith("Endconn~")) break;

            while (true) {
                System.out.print("[you]: ");
                message = scan.nextLine();
                server.sendMessage(message);

                if (message.endsWith("~")) break;
            }
            
        }

        scan.close();
    }
}

public class Main {
    public static void main(String[] args) {
        new App();
    }
}
