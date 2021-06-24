package concurrency;

import java.io.IOException;

public class ServerThread {
    public static void main(String[] args){
        try {
            Server server = new Server(18009, 1000);
//            server.setBusinessHandle(new OneThreadScheduler());
//            server.setClientScheduler(new ThreadPerRequestScheduler());
//            server.setClientScheduler(new ExcutorClientScheduler(10));
            server.setClientScheduler(new CallableScheduler(10));
            Thread thread = new Thread(server);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
