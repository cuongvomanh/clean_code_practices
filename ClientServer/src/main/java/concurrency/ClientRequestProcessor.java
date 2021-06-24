package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ClientRequestProcessor {
    private ClientConnect clientConnect;
    public ClientRequestProcessor(ClientConnect clientConnect) {
        this.clientConnect = clientConnect;
    }

    public InputStream getInputStream() throws IOException {
        return clientConnect.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return clientConnect.getOutputStream();
    }

    public void close() throws IOException {
        clientConnect.close();
    }
}
