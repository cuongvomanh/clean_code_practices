package concurrency;

import java.io.IOException;

public class ServerThread {
    public static void main(String[] args){
        try {
            Server server = new Server(18009, 1000);
//            server.setBusinessHandle(new OneThreadBussinessHandle());
            server.setBusinessHandle(new ThreadPerRequestBussinessHandle());
            Thread thread = new Thread(server);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
