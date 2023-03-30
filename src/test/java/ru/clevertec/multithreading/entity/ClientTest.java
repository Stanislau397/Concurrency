package ru.clevertec.multithreading.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ClientTest {

    private static final Integer NUMBER_OF_ELEMENTS = 10;
    private Client client;
    private Server server;

    @BeforeEach
    void setUp() {
        client = new Client(NUMBER_OF_ELEMENTS);
        server = new Server();
    }

    @AfterEach
    void tierDown() {
        client = null;
        server = null;
    }

    @Test
    void checkSendAllRequestsShouldReturnAccumulator() {
        int expectedAccumulator = 55;

        int actualAccumulator = client.sendAllRequests(client.getRequests(), server);

        assertThat(actualAccumulator).isEqualTo(expectedAccumulator);
    }

    @Test
    void checkSendAllRequestsShouldReturnCorrectSizeForResponseList() {
        int expectedSize = 10;

        client.sendAllRequests(client.getRequests(), server);

        int actualSize = server.getServerResponses().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    void checkSendAllRequestsShouldReturnEmptyRequestsList() {
        int expectedSize = 0;

        client.sendAllRequests(client.getRequests(), server);

        int actualSize = client.getRequests().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }
}