package concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    private List<ClientConnect> clientConnectPoll;
    private ServerSocket serverSocket;

    public ConnectionManager(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientConnectPoll = new ArrayList<>();
    }

    public ClientConnect awaitClient() throws IOException {
        ClientConnect clientConnect = getClient();
        return clientConnect;
    }

    private ClientConnect getClient() throws IOException {
        clientConnectPoll.add(new ClientConnect(serverSocket));
        int clientIndex = getLastPollIndex();
        System.out.printf("Client %2d: connection\n", clientIndex);
        ClientConnect clientConnect = clientConnectPoll.get(clientIndex);
        return clientConnect;
    }

    private int getLastPollIndex() {
        return clientConnectPoll.size() - 1;
    }
}
