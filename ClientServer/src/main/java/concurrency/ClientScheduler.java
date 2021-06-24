package concurrency;

import java.io.IOException;

public abstract class ClientScheduler {
    public abstract void schedule(ClientRequestProcessor clientRequestProcessor);

    public void closeIgnoringException(ClientRequestProcessor clientRequestProcessor) {
        if (clientRequestProcessor != null){
            try {
                clientRequestProcessor.close();
            } catch (IOException ignore){
            }
        }
    }
}
