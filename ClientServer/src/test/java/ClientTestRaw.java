import concurrency.*;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

/**
 * @author myeongju.jung
 */
public class ClientTestRaw {
    private static final int PORT = 18009;
    private static final int TIMEOUT = 2000;

    Server server;
    Thread serverThread;

//    @Before
    public void createServer() throws Exception {
        try {
            server = new Server(PORT, TIMEOUT);
            serverThread = new Thread(server);
            serverThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @After
    public void shutdownServer() throws InterruptedException {
        if (server != null) {
            server.stopProcessing();
            serverThread.join();
        }
    }

    class TrivialClient implements Runnable {
        int clientNumber;

        TrivialClient(int clientNumber) {
            this.clientNumber = clientNumber;
        }

        @Override
        public void run() {
            try {
                connectSendReceive(clientNumber);
            } catch (IOException e) {
                e.printStackTrace();;
            }
        }
    }

    private void connectSendReceive(int i) throws IOException {
        ConnectionManager connectionManager = new ConnectionManager(PORT);
        ClientConnect clientConnect = connectionManager.awaitClient();
        ClientRequestProcessor clientRequestProcessor = new ClientRequestProcessor(clientConnect);
//        System.out.printf("Client %2d: connection\n", i);
//        Socket socket = new Socket("localhost", PORT);
        System.out.printf("Client %2d: sending message\n", i);
        MessageUtils.sendMessage(clientRequestProcessor, "M" + Integer.toString(i));
        System.out.printf("Client %2d: getting reply\n", i);
        MessageUtils.getMessage(clientRequestProcessor);
        System.out.printf("Client %2d: finished\n", i);
        socket.close();
    }

    @Test(timeout = 10000)
    public void shouldRunInUnder10Seconds() throws Exception {
        Thread[] threads = new Thread[10];
        createAndStartAllThreads(threads);
        waitForAllThreadsToFinish(threads);

    }

    private void createAndStartAllThreads(Thread[] threads) {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new TrivialClient(i));
            threads[i].start();
        }
    }

    private void waitForAllThreadsToFinish(Thread[] threads) throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }
}