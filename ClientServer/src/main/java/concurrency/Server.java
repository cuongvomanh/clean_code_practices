package concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Runnable {
    volatile boolean keepProcessing = true;
    private ClientScheduler clientScheduler;
    private ConnectionManager connectionManager;
    private int port;
    private int millisecondsTimeout;

    public Server(int port, int millisecondsTimeout) throws IOException {
        this.port = port;
        this.millisecondsTimeout = millisecondsTimeout;

    }

    @Override
    public void run() {

        try {
            System.out.println("Server starting");
            connectionManager = new ConnectionManager(18009);
            while (keepProcessing){
                try {
                    ClientConnect clientConnect = connectionManager.awaitClient();
                    clientConnect.setSoTimeout(millisecondsTimeout);
                    ClientRequestProcessor requestProcessor = new ClientRequestProcessor(clientConnect);
                    clientScheduler.schedule(requestProcessor);
                } catch (Exception e){
                    handle(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handle(Exception e) {
        if (! (e instanceof SocketException)){
            e.printStackTrace();
        }
    }


    public void stopProcessing() {
    }

    public void setClientScheduler(ClientScheduler clientScheduler) {
        this.clientScheduler = clientScheduler;
    }
}
