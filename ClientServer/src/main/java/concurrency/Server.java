package concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Runnable {
    ServerSocket serverSocket;
    volatile boolean keepProcessing = true;
    private BusinessHandle businessHandle;

    public Server(int port, int millisecondsTimeout) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(millisecondsTimeout);
    }

    @Override
    public void run() {
        System.out.println("Server starting");
        while (keepProcessing){
            try {
                System.out.println("accepting client");
                Socket socket = serverSocket.accept();
                System.out.println("got client");
                businessHandle.process(socket);
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

    public void setBusinessHandle(BusinessHandle businessHandle) {
        this.businessHandle = businessHandle;
    }
}
