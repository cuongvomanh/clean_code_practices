package concurrency;

import java.io.IOException;

public interface ClientScheduler {
    public void schedule(ClientRequestProcessor clientRequestProcessor);
}
