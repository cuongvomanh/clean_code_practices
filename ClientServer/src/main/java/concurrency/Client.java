package concurrency;

import java.net.Socket;

public class Client {
    private static final int PORT = 8009;

    public void connectSendReceive(int i){
        try {
            Socket socket = new Socket("localhost", PORT);
            MessageUtils.sendMessage(socket, Integer.toString(i));
            MessageUtils.getMessage(socket);
            socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
