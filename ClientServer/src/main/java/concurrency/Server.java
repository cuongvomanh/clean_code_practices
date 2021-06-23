package concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Runnable {
    ServerSocket serverSocket;
    volatile boolean keepProcessing = true;

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
                process(socket);
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

    private static void process(Socket socket) {
        if (socket == null) return;
        try {
            System.out.println("Server: getting message");
            String message = MessageUtils.getMessage(socket);
            System.out.println(String.format("Server: got message %s", message));
            Thread.sleep(1000);
            System.out.println(String.format("Server: sending reply: %s", message));
            MessageUtils.sendMessage(socket, "Processed: " + message);
            System.out.println("Server: sent");
            closeIgnoringException(socket);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void closeIgnoringException(Socket socket) {
        if (socket != null){
            try {
                socket.close();
            } catch (IOException ignore){
            }
        }
    }


}
