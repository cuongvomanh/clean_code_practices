package concurrency;

public class OneThreadScheduler implements ClientScheduler {

    @Override
    public void schedule(ClientRequestProcessor clientRequestProcessor) {
        if (clientRequestProcessor == null) return;
        try {
            clientRequestProcessor.process();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
