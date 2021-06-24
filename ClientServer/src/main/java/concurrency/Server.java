package concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Runnable {
    ServerSocket serverSocket;
    volatile boolean keepProcessing = true;
    private ClientScheduler clientScheduler;
    private ConnectionManager connectionManager;

    public Server(int port, int millisecondsTimeout) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(millisecondsTimeout);
    }

    @Override
    public void run() {
        System.out.println("Server starting");
        while (keepProcessing){
            try {
                connectionManager = new ConnectionManager(18009);
                ClientConnect clientConnect = connectionManager.awaitClient();
                ClientRequestProcessor requestProcessor = new ClientRequestProcessor(clientConnect);
                clientScheduler.schedule(requestProcessor);
            } catch (Exception e){
                handle(e);
            }
        }
    }

    private static void handle(Exception e) {
        if (! (e instanceof SocketException)){
            e.printStackTrace();
        }
    }


    public void stopProcessing() {
    }

    public void setBusinessHandle(ClientScheduler clientScheduler) {
        this.clientScheduler = clientScheduler;
    }
}
