package concurrency;

import java.io.IOException;
import java.util.concurrent.*;

public class CallableScheduler implements ClientScheduler{
    ExecutorService executorService;

    public CallableScheduler(int availableThreads) {
        executorService = Executors.newFixedThreadPool(availableThreads);
    }
    @Override
    public void schedule(ClientRequestProcessor clientRequestProcessor) {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    clientRequestProcessor.process();
                    return "Process success!";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Process fail!";
                }
            }
        };
        Future<String> result = executorService.submit(callable);
        try {
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
