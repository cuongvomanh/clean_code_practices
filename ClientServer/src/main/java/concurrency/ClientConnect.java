package concurrency;

public class ClientConnect {
    public static void main(String[] args){
        Client client = new Client();
        client.connectSendReceive(8009);
    }
}
