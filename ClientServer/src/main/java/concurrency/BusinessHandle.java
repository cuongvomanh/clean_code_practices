package concurrency;

import java.io.IOException;
import java.net.Socket;

public abstract class BusinessHandle {
    public abstract void process(Socket socket);

    public void closeIgnoringException(Socket socket) {
        if (socket != null){
            try {
                socket.close();
            } catch (IOException ignore){
            }
        }
    }
}
