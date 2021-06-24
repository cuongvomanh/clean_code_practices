package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ClientConnect {
    Socket socket;
    private int id;

    public ClientConnect(Socket socket) throws IOException {
        this.socket = socket;
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public void close() throws IOException {
        socket.close();
    }

    public void setSoTimeout(int millisecondsTimeout) throws SocketException {
        socket.setSoTimeout(millisecondsTimeout);
    }
}
