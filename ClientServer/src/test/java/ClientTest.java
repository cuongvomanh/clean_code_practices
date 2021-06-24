import org.junit.Test;

public class ClientTest {
    @Test
    public void shouldRunInUnder10Seconds(){
        Thread[] threads = createThreads();
        startAllThreadsw(threads);
        waitForAllThreadsToFinish(threads);
    }

    private void waitForAllThreadsToFinish(Thread[] threads) {
    }

    private void startAllThreadsw(Thread[] threads) {
        
    }

    private Thread[] createThreads() {
        return null;
    }
}
