package concurrency;

import java.net.Socket;

public class Client {
    private static final int PORT = 8009;

    public void connectSendReceive(int i){
        try {
            Socket socket = new Socket("localhost", PORT);
            ConnectionManager connectionManager = new ConnectionManager();
            ClientConnect clientConnect = connectionManager.awaitClient();
            ClientRequestProcessor clientRequestProcessor = new ClientRequestProcessor(clientConnect);
            MessageUtils.sendMessage(clientRequestProcessor, Integer.toString(i));
            MessageUtils.getMessage(clientRequestProcessor);
            socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
