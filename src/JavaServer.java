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

                // Lese die Anforderung des Clients
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = input.readLine();
                System.out.println("Anfrage: " + request);

                // Extrahiere den Text aus der URL
                String[] parts = request.split("\\?");
                String text = "";
                if (parts.length > 1) {
                    String[] params = parts[1].split("=");
                    if (params.length > 1 && params[0].equals("text")) {
                        text = params[1];
                    }
                }

                // Verarbeite den empfangenen Text
                System.out.println("Empfangener Text: " + text);

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
