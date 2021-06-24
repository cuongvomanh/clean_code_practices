package concurrency;

import java.net.Socket;

public class Client {
    private static final int PORT = 8009;

    public void connectSendReceive(int i){
        try {
            ConnectionManager connectionManager = new ConnectionManager(PORT);
            ClientConnect clientConnect = connectionManager.awaitClient();
            MessageUtils.sendMessage(clientConnect, Integer.toString(i));
            MessageUtils.getMessage(clientConnect);
            clientConnect.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
