package ru.clevertec.multithreading.runner;

import ru.clevertec.multithreading.entity.Client;
import ru.clevertec.multithreading.entity.Server;

public class Runner {

    public static void main(String[] args) {
        int numberOfElements = 100;
        int numberOfThreads = 8;
        Client client = new Client(numberOfElements, numberOfThreads);
        Server server = new Server();
        client.sendAllRequests(client.getRequests(), server);
    }
}
