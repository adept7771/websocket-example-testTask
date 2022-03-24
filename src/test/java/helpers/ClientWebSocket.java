package helpers;

import etc.Settings;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

// не работал раньше с вебсокетами и не знаю какую все таки либу надо использовать по-правильному,
// либа TooTallNate/Java-WebSocket мне показалась наиболее простой как для запуска с пол-пинка.
// единственно, что респонсы возвращает строчками. Зато под капотом уже интерфейс runnable, который
// запускает отдельный тред! В моих тестах я это не обыгрывал, но это можно использовать в дальнейшем
// для многопоточки.

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
//        System.out.println(
//                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
//                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public ArrayList<String> getResponses(int messagesToGet) throws InterruptedException {
        this.connect();
        long totalPollingInterval = 0;
        while (responseMessages.size() < messagesToGet) {
            Thread.sleep(Settings.pollingResponseIntervalMs);
            totalPollingInterval += Settings.pollingResponseIntervalMs;
            if (totalPollingInterval > Settings.pollingResponseIntervalMaxTimeMs) {
                break;
            }
        }
        this.close();
        Thread.currentThread().interrupt();
        return responseMessages;
    }
}
