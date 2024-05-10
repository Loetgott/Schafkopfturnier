import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class Server {
    public Server(){
        int port = 8000; // Portnummer für den Server
        HttpServer server = null;
        try {
            server = HttpServer.create(new java.net.InetSocketAddress(port), 0);
        } catch (IOException e) {
            System.out.println(Game.RED + "Fehler beim Erstellen des Servers!" + Game.RESET);
        }
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
                //System.out.println("POST-Anfrage empfangen!"); // Ausgabe in der Konsole für Debug-Zwecke
                // Lese den Text aus dem Request-Body
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String text = br.readLine();
                // Gib den empfangenen Text in der Konsole aus
                String clientIP = exchange.getRemoteAddress().getAddress().getHostAddress();
                System.out.println("Empfangener Text: " + text);
                ArrayList<String> input = new ArrayList<>(Arrays.asList(text.split(" ")));
                switch(input.get(0)){
                    case "aufgerufen":
                        Game.connectNewUser(new WebUser(clientIP));
                        input.clear();
                        break;
                    case "name":
                        System.out.println("Namenswechsel beantragt");
                        input.remove(0);
                        if(!input.isEmpty()){
                            String vorname = input.get(0);
                            String nachname = " ";
                            input.remove(0);
                            if(!input.isEmpty()){
                                nachname = input.get(0);
                            }
                            Game.giveWebUserName(clientIP,vorname + " " + nachname);
                        }
                        break;
                    case "ping":

                        break;
                    case "getTische":
                            input.remove(0);
                            if(!input.isEmpty()){
                                int tischNumber = Integer.parseInt(input.get(0));
                                System.out.println("Tisch " + tischNumber + " ausgewählt!");
                                StringBuilder response = new StringBuilder("");
                                response.append(Game.tischList.get(tischNumber - 1).playerList.get(0).getName()[0]).append(" ").append(Game.tischList.get(tischNumber - 1).playerList.get(0).getName()[1].charAt(0)).append(".");
                                response.append(";");
                                response.append(Game.tischList.get(tischNumber - 1).playerList.get(1).getName()[0]).append(" ").append(Game.tischList.get(tischNumber - 1).playerList.get(1).getName()[1].charAt(0)).append(".");
                                response.append(";");
                                response.append(Game.tischList.get(tischNumber - 1).playerList.get(2).getName()[0]).append(" ").append(Game.tischList.get(tischNumber - 1).playerList.get(2).getName()[1].charAt(0)).append(".");
                                response.append(";");
                                response.append(Game.tischList.get(tischNumber - 1).playerList.get(3).getName()[0]).append(" ").append(Game.tischList.get(tischNumber - 1).playerList.get(3).getName()[1].charAt(0)).append(".");
                                response.append(";");
                                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                                exchange.sendResponseHeaders(200, response.toString().getBytes().length); // Setze den HTTP-Statuscode und die Länge der Antwort
                                exchange.getResponseBody().write(response.toString().getBytes());
                                exchange.getResponseBody().close();
                            }
                        break;

                }

                // Sende eine Bestätigungsnachricht an den Client
                //String response = "Text empfangen: " + text;
                //exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                //exchange.sendResponseHeaders(200, response.getBytes().length); // Setze den HTTP-Statuscode und die Länge der Antwort
                //exchange.getResponseBody().write(response.getBytes());
                //exchange.getResponseBody().close();
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
                Gui.disconnectWebUser(clientIP);

                // (z. B. in eine Datenbank einfügen)
                exchange.sendResponseHeaders(200, -1); // Bestätigungsnachricht senden (kein Inhalt)
                exchange.getResponseBody().close();
            } else {
                System.out.println("Ungültige Anfrage-Methode: " + exchange.getRequestMethod()); // Ausgabe in der Konsole für Debug-Zwecke
            }
        }
    }
}