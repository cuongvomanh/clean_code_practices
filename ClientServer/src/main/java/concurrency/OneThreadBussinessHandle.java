package concurrency;

import java.net.Socket;

public class OneThreadBussinessHandle extends BusinessHandle {

    @Override
    public void process(Socket socket) {
        if (socket == null) return;
        try {
            System.out.println("Server: getting message");
            String message = MessageUtils.getMessage(socket);
            System.out.println(String.format("Server: got message %s", message));
            Thread.sleep(1000);
            System.out.println(String.format("Server: sending reply: %s", message));
            MessageUtils.sendMessage(socket, "Processed: " + message);
            System.out.println("Server: sent");
            closeIgnoringException(socket);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
