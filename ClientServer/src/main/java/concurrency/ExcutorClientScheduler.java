package concurrency;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExcutorClientScheduler implements ClientScheduler {
    Executor executor;

    public ExcutorClientScheduler(int availableThreads) {
        executor = Executors.newFixedThreadPool(availableThreads);
    }

    @Override
    public void schedule(ClientRequestProcessor clientRequestProcessor) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    clientRequestProcessor.process();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        executor.execute(runnable);
    }
}
