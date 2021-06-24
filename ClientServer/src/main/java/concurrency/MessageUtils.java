package concurrency;

import java.io.*;

public class MessageUtils {
    public static void sendMessage(ClientRequestProcessor clientRequestProcessor, String message) throws IOException {
        OutputStream stream = clientRequestProcessor.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeUTF(message);
        oos.flush();
    }

    public static String getMessage(ClientRequestProcessor clientRequestProcessor) throws IOException {
        InputStream stream = clientRequestProcessor.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(stream);
        return ois.readUTF();
    }
}
