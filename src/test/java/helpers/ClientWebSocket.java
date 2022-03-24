package helpers;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;


public class ClientWebSocket extends WebSocketClient {

    private ArrayList<String> responseMessages = new ArrayList<>();
    private int maximumOfResponses;

    public ClientWebSocket(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public ClientWebSocket(URI serverURI) {
        super(serverURI);
    }

    public ClientWebSocket(String serverURI) {
        super(URI.create(serverURI));
    }

    public ClientWebSocket(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        //System.out.println("Opened connection");
    }

    @Override
    public void onMessage(String message) {
        //System.out.println(Thread.currentThread() + " thread, message received: " + message);
        responseMessages.add(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public ArrayList<String> getResponses(int messagesToGet) throws InterruptedException {
        this.connect();
        while (responseMessages.size() < messagesToGet) {
            Thread.sleep(10);
        }
        this.close();
        Thread.currentThread().interrupt();
        return responseMessages;
    }
}
