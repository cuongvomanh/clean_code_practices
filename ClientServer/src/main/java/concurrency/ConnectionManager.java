package concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    private final int port;
    private ServerSocket serverSocket;

    public ConnectionManager(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
    }

    public ClientConnect awaitClient() throws IOException {
        System.out.println("accepting client");
        ClientConnect clientConnect = getClient();
        System.out.println("got client");
        return clientConnect;
    }

    private ClientConnect getClient() throws IOException {
        ClientConnect clientConnect = new ClientConnect(serverSocket.accept());
        return clientConnect;
    }

}
