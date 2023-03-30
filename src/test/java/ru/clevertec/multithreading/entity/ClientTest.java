package ru.clevertec.multithreading.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ClientTest {

    private static final Integer NUMBER_OF_ELEMENTS = 10;
    private static final Integer NUMBER_OF_THREADS = 10;
    private Client client;
    private Server server;

    @BeforeEach
    void setUp() {
        client = new Client(NUMBER_OF_ELEMENTS, NUMBER_OF_THREADS);
        server = new Server();
    }

    @AfterEach
    void tierDown() {
        client = null;
        server = null;
    }

    @Test
    void checkSendAllRequestsShouldReturnAccumulator() {
        int expectedAccumulator = (1 + NUMBER_OF_ELEMENTS) * (NUMBER_OF_ELEMENTS / 2);

        client.sendAllRequests(server);

        int actualAccumulator = client.getAccumulator().get();

        assertThat(actualAccumulator).isEqualTo(expectedAccumulator);
    }

    @Test
    void checkSendAllRequestsShouldReturnResponsesListWith10Elements() {
        int expectedSize = 10;

        client.sendAllRequests(server);

        int actualSize = server.getServerResponses().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    void checkSendAllRequestsShouldReturnRequestsListWithZeroElements() {
        int expectedSize = 0;

        client.sendAllRequests(server);

        int actualSize = client.getRequests().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }
}