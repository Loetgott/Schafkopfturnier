import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class Server {
    public Server(){
        int port = 8000;
        HttpServer server = null;
        try {
            server = HttpServer.create(new java.net.InetSocketAddress(port), 0);
        } catch (IOException e) {
            System.out.println(Game.RED + "Fehler beim Erstellen des Servers!" + Game.RESET);
            return;
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
            // CORS-Header einmal hinzufügen
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1); // Kein Inhalt für OPTIONS-Antwort
                return;
            } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String text = br.readLine();
                String clientIP = exchange.getRemoteAddress().getAddress().getHostAddress();
                ArrayList<String> input = new ArrayList<>(Arrays.asList(text.split(" ")));

                switch (input.get(0)) {
                    case "aufgerufen":
                        Game.connectNewUser(new WebUser(clientIP));
                        break;
                    case "name":
                        if (input.size() >= 2) {
                            String vorname = input.get(1);
                            String nachname = (input.size() > 2) ? input.get(2) : "";
                            Game.giveWebUserName(clientIP, vorname + " " + nachname);
                        }
                        break;
                    case "getTische":
                        handleGetTische(exchange, input, clientIP);
                        break;
                    case "setPoints":
                        handleSetPoints(exchange, input, clientIP);
                        break;
                    case "setPointsSucces":
                        System.out.println(Game.GREEN + "Punkte von " + Objects.requireNonNull(Game.getWebUser(clientIP)).getName() + " erfolgreich empfangen!" + Game.RESET);
                        Objects.requireNonNull(Game.getWebUser(clientIP)).addPlayerPoints();
                        break;
                    default:
                        System.out.println(Game.RED + "Unbekannte Anfrage vom Server! Bitte prüfen");
                        System.out.println(input.get(0) + Game.RESET);
                }
            } else {
                System.out.println("Ungültige Anfrage-Methode: " + exchange.getRequestMethod());
            }
        }

        private void handleGetTische(HttpExchange exchange, ArrayList<String> input, String clientIP) throws IOException {
            if (input.size() > 1) {
                int tischNumber = Integer.parseInt(input.get(1));
                StringBuilder response = new StringBuilder();

                for (int i = 0; i < 4; i++) {
                    response.append(Game.tischList.get(tischNumber - 1).playerList.get(i).getName()[0])
                            .append(" ")
                            .append(Game.tischList.get(tischNumber - 1).playerList.get(i).getName()[1].charAt(0))
                            .append(".;")
                            .append(Game.tischList.get(tischNumber - 1).playerList.get(i).getPoints())
                            .append(";")
                            .append(Game.tischList.get(tischNumber - 1).playerList.get(i).hasRoundPoints)
                            .append(";")
                            .append(Game.tischList.get(tischNumber - 1).playerList.get(i).roundPoints)
                            .append(";");
                }
                String responseStr = response.toString();
                exchange.sendResponseHeaders(200, responseStr.getBytes().length);
                exchange.getResponseBody().write(responseStr.getBytes());
                exchange.getResponseBody().close();
            }
        }

        private void handleSetPoints(HttpExchange exchange, ArrayList<String> input, String clientIP) throws IOException {
            System.out.println(input);
            if (input.size() >= 15) {
                WebUser user = Objects.requireNonNull(Game.getWebUser(clientIP));
                user.tempTischNumber = Integer.parseInt(input.get(1));
                user.tempPlayer1 = createPlayer(input, 2, 3, 4, 5);
                user.tempPlayer2 = createPlayer(input, 6, 7, 8, 9);
                user.tempPlayer3 = createPlayer(input, 10, 11, 12, 13);
                user.tempPlayer4 = createPlayer(input, 14, 15, 16, 17);

                StringBuilder response = new StringBuilder();
                response.append(user.tempPlayer1.getVorname()).append(" ").append(user.tempPlayer1.getNachname()).append(";").append(user.tempPlayer1.getPoints()).append(";");
                response.append(user.tempPlayer2.getVorname()).append(" ").append(user.tempPlayer2.getNachname()).append(";").append(user.tempPlayer2.getPoints()).append(";");
                response.append(user.tempPlayer3.getVorname()).append(" ").append(user.tempPlayer3.getNachname()).append(";").append(user.tempPlayer3.getPoints()).append(";");
                response.append(user.tempPlayer4.getVorname()).append(" ").append(user.tempPlayer4.getNachname()).append(";").append(user.tempPlayer4.getPoints());

                String responseStr = response.toString();
                exchange.sendResponseHeaders(200, responseStr.getBytes().length);
                exchange.getResponseBody().write(responseStr.getBytes());
                exchange.getResponseBody().close();
            }
        }

        private Player createPlayer(ArrayList<String> input, int nameIndex, int vornameIndex, int pointsIndex, int statusIndex) {
            Player player = new Player(input.get(nameIndex), input.get(vornameIndex));
            player.setPoints(Integer.parseInt(input.get(pointsIndex)));
            if (!Objects.equals(input.get(statusIndex), "n/a")) {
                switch (input.get(statusIndex)) {
                    case "up":
                        player.steigtAuf = true;
                        player.nextTischSet = true;
                        break;
                    case "down":
                        player.steigtAuf = false;
                        player.nextTischSet = true;
                        break;
                }
            }
            return player;
        }
    }

    static class SendTextOnExitHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // CORS-Header einmal hinzufügen
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String text = br.readLine();
                String clientIP = exchange.getRemoteAddress().getAddress().getHostAddress();
                System.out.println(Game.getWebUser(clientIP).getName() + " hat die Seite verlassen!");
                Gui.disconnectWebUser(clientIP);

                exchange.sendResponseHeaders(200, -1); // Bestätigungsnachricht senden (kein Inhalt)
                exchange.getResponseBody().close();
            } else {
                System.out.println("Ungültige Anfrage-Methode: " + exchange.getRequestMethod());
            }
        }
    }
}
