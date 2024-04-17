import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            System.out.println("Server gestartet. Warte auf Verbindung...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Verbindung hergestellt.");

                // Lese die IP-Adresse des Clients
                String clientIP = socket.getInetAddress().getHostAddress();
                System.out.println("Anfrage von IP-Adresse: " + clientIP);

                // Lese die Anforderung des Clients
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = input.readLine();
                System.out.println("Anfrage: " + request);

                // Verarbeite die Anfrage ...

                // Sende eine Antwort zurück zum Client
                // (In diesem Beispiel wird keine Antwort gesendet)

                // Schließe die Verbindung
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
