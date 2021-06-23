package concurrency;

import java.io.IOException;

public class ServerThread {
    public static void main(String[] args){
//        Client client = new Client();
//        client.connectSendReceive(8009);
        try {
            Server server = new Server(8009, 1000);
            Thread thread = new Thread(server);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
