package concurrency;

public class OneThreadBussinessHandle extends ClientScheduler {

    @Override
    public void schedule(ClientRequestProcessor clientRequestProcessor) {
        if (clientRequestProcessor == null) return;
        try {
            System.out.println("Server: getting message");
            String message = MessageUtils.getMessage(clientRequestProcessor);
            System.out.println(String.format("Server: got message %s", message));
            Thread.sleep(1000);
            System.out.println(String.format("Server: sending reply: %s", message));
            MessageUtils.sendMessage(clientRequestProcessor, "Processed: " + message);
            System.out.println("Server: sent");
            closeIgnoringException(clientRequestProcessor);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
