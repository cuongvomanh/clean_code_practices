package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ClientRequestProcessor {
    private ClientConnect clientConnect;
    public ClientRequestProcessor(ClientConnect clientConnect) {
        this.clientConnect = clientConnect;
    }

    public void process() throws IOException, InterruptedException {
        System.out.println("Server: getting message");
        String message = MessageUtils.getMessage(clientConnect);
        System.out.println(String.format("Server: got message %s", message));
        Thread.sleep(1000);
        System.out.println(String.format("Server: sending reply: %s", message));
        MessageUtils.sendMessage(clientConnect, "Processed: " + message);
        System.out.println("Server: sent");
        closeIgnoringException();
    }

    public void closeIgnoringException() {
        if (clientConnect != null){
            try {
                clientConnect.close();
            } catch (IOException ignore){
            }
        }
    }

}
