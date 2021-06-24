package concurrency;

public class ThreadPerRequestScheduler implements ClientScheduler {
    @Override
    public void schedule(ClientRequestProcessor clientRequestProcessor) {
        if (clientRequestProcessor == null) return;
        Runnable clientHandle = new Runnable() {
            @Override
            public void run() {
                try {
                    clientRequestProcessor.process();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        Thread clientConnection = new Thread(clientHandle);
        clientConnection.start();
    }
}
