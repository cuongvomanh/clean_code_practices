package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnect {
    Socket serverSocket;
    private int id;

    public ClientConnect(ServerSocket serverSocket) throws IOException {
        System.out.println("accepting client");
        System.out.println("got client");
        this.serverSocket = serverSocket.accept();
    }

    public InputStream getInputStream() throws IOException {
        return serverSocket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return serverSocket.getOutputStream();
    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
