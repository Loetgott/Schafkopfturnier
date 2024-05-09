import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class ServerTest {
    public static void main(String[] args) throws IOException {
        int port = 8000; // Portnummer für den Server
        HttpServer server = HttpServer.create(new java.net.InetSocketAddress(port), 0);
        server.createContext("/sendText", new SendTextHandler());
        server.createContext("/sendTextOnExit", new SendTextOnExitHandler());
        server.setExecutor(null); // Default-Executor verwenden
        server.start();
        System.out.println("Server gestartet auf Port " + port);
    }

    static class SendTextHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                // Antwort auf die OPTIONS-Anfrage senden
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
                exchange.sendResponseHeaders(204, -1);
            } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                System.out.println("POST-Anfrage empfangen!"); // Ausgabe in der Konsole für Debug-Zwecke
                // Lese den Text aus dem Request-Body
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String text = br.readLine();
                // Gib den empfangenen Text in der Konsole aus
                String clientIP = exchange.getRemoteAddress().getAddress().getHostAddress();
                System.out.println("Empfangener Text: " + text);


                // Sende eine Bestätigungsnachricht an den Client
                String response = "Text empfangen: " + text;
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, response.getBytes().length); // Setze den HTTP-Statuscode und die Länge der Antwort
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            } else {
                System.out.println("Ungültige Anfrage-Methode: " + exchange.getRequestMethod()); // Ausgabe in der Konsole für Debug-Zwecke
            }
        }
    }

    static class SendTextOnExitHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                System.out.println("Text beim Verlassen der Seite empfangen!"); // Ausgabe in der Konsole für Debug-Zwecke
                // Lese den Text aus dem Request-Body
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String text = br.readLine();
                // Gib den empfangenen Text in der Konsole aus
                String clientIP = exchange.getRemoteAddress().getAddress().getHostAddress();
                System.out.println("Empfangener Text beim Verlassen der Seite: " + text);
                // Hier könntest du den empfangenen Text speichern oder verarbeiten


                // (z. B. in eine Datenbank einfügen)
                exchange.sendResponseHeaders(200, -1); // Bestätigungsnachricht senden (kein Inhalt)
                exchange.getResponseBody().close();
            } else {
                System.out.println("Ungültige Anfrage-Methode: " + exchange.getRequestMethod()); // Ausgabe in der Konsole für Debug-Zwecke
            }
        }
    }
}
