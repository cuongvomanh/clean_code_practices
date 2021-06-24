package concurrency;

import java.io.*;

public class MessageUtils {
    public static void sendMessage(ClientConnect clientConnect, String message) throws IOException {
        OutputStream stream = clientConnect.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeUTF(message);
        oos.flush();
    }

    public static String getMessage(ClientConnect clientConnect) throws IOException {
        InputStream stream = clientConnect.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(stream);
        return ois.readUTF();
    }
}
