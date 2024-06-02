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
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
                exchange.sendResponseHeaders(204, -1); // Kein Inhalt für OPTIONS-Antwort
                return;
            } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                // Lese den Text aus dem Request-Body
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String text = br.readLine();
                String clientIP = exchange.getRemoteAddress().getAddress().getHostAddress();
                ArrayList<String> input = new ArrayList<>(Arrays.asList(text.split(" ")));
                switch(input.get(0)){
                    case "aufgerufen":
                        Game.connectNewUser(new WebUser(clientIP));
                        input.clear();
                        break;
                    case "name":
                        input.remove(0);
                        if(!input.isEmpty()){
                            String vorname = input.get(0);
                            String nachname = " ";
                            input.remove(0);
                            if(!input.isEmpty()){
                                nachname = input.get(0);
                            }
                            Game.giveWebUserName(clientIP, vorname + " " + nachname);
                        }
                        break;
                    case "ping":
                        // Ping-Handling-Code hier
                        break;
                    case "getTische":
                        input.remove(0);
                        if(!input.isEmpty()){
                            int tischNumber = Integer.parseInt(input.get(0));
                            StringBuilder response = new StringBuilder();
                            response.append(Game.tischList.get(tischNumber - 1).playerList.get(0).getName()[0]).append(" ").append(Game.tischList.get(tischNumber - 1).playerList.get(0).getName()[1].charAt(0)).append(".");
                            response.append(";");
                            response.append(Game.tischList.get(tischNumber - 1).playerList.get(1).getName()[0]).append(" ").append(Game.tischList.get(tischNumber - 1).playerList.get(1).getName()[1].charAt(0)).append(".");
                            response.append(";");
                            response.append(Game.tischList.get(tischNumber - 1).playerList.get(2).getName()[0]).append(" ").append(Game.tischList.get(tischNumber - 1).playerList.get(2).getName()[1].charAt(0)).append(".");
                            response.append(";");
                            response.append(Game.tischList.get(tischNumber - 1).playerList.get(3).getName()[0]).append(" ").append(Game.tischList.get(tischNumber - 1).playerList.get(3).getName()[1].charAt(0)).append(".");
                            response.append(";");
                            response.append(Game.tischList.get(tischNumber - 1).playerList.get(0).getPoints());
                            response.append(";");
                            response.append(Game.tischList.get(tischNumber - 1).playerList.get(1).getPoints());
                            response.append(";");
                            response.append(Game.tischList.get(tischNumber - 1).playerList.get(2).getPoints());
                            response.append(";");
                            response.append(Game.tischList.get(tischNumber - 1).playerList.get(3).getPoints());
                            response.append(";");
                            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
                            exchange.getResponseBody().write(response.toString().getBytes());
                            exchange.getResponseBody().close();
                        }
                        break;
                    case "setPoints":
                        input.remove(0);
                        Objects.requireNonNull(Game.getWebUser(clientIP)).tempTischNumber = Integer.parseInt(input.get(0));
                        Objects.requireNonNull(Game.getWebUser(clientIP)).tempPlayer1 = new Player(input.get(1), input.get(2));
                        Objects.requireNonNull(Game.getWebUser(clientIP)).tempPlayer1.setPoints(Integer.parseInt(input.get(3)));
                        if(!Objects.equals(input.get(4), "n/a")){
                            switch(input.get(4)){
                                case "up":
                                    Game.getWebUser(clientIP).tempPlayer1.steigtAuf = true;
                                    Game.getWebUser(clientIP).tempPlayer1.nextTischSet = true;
                                    break;
                                case "down":
                                    Game.getWebUser(clientIP).tempPlayer1.steigtAuf = false;
                                    Game.getWebUser(clientIP).tempPlayer1.nextTischSet = true;
                            }
                        }
                        Objects.requireNonNull(Game.getWebUser(clientIP)).tempPlayer2 = new Player(input.get(5), input.get(6));
                        Objects.requireNonNull(Game.getWebUser(clientIP)).tempPlayer2.setPoints(Integer.parseInt(input.get(7)));
                        if(!Objects.equals(input.get(8), "n/a")){
                            switch(input.get(8)){
                                case "up":
                                    Game.getWebUser(clientIP).tempPlayer2.steigtAuf = true;
                                    Game.getWebUser(clientIP).tempPlayer2.nextTischSet = true;
                                    break;
                                case "down":
                                    Game.getWebUser(clientIP).tempPlayer2.steigtAuf = false;
                                    Game.getWebUser(clientIP).tempPlayer2.nextTischSet = true;
                            }
                        }
                        Objects.requireNonNull(Game.getWebUser(clientIP)).tempPlayer3 = new Player(input.get(9), input.get(10));
                        Objects.requireNonNull(Game.getWebUser(clientIP)).tempPlayer3.setPoints(Integer.parseInt(input.get(11)));
                        if(!Objects.equals(input.get(12), "n/a")){
                            switch(input.get(12)){
                                case "up":
                                    Game.getWebUser(clientIP).tempPlayer3.steigtAuf = true;
                                    Game.getWebUser(clientIP).tempPlayer3.nextTischSet = true;
                                    break;
                                case "down":
                                    Game.getWebUser(clientIP).tempPlayer3.steigtAuf = false;
                                    Game.getWebUser(clientIP).tempPlayer3.nextTischSet = true;
                            }
                        }
                        Objects.requireNonNull(Game.getWebUser(clientIP)).tempPlayer4 = new Player(input.get(13), input.get(12));
                        Objects.requireNonNull(Game.getWebUser(clientIP)).tempPlayer4.setPoints(Integer.parseInt(input.get(13)));
                        if(!Objects.equals(input.get(14), "n/a")){
                            switch(input.get(14)){
                                case "up":
                                    Game.getWebUser(clientIP).tempPlayer4.steigtAuf = true;
                                    Game.getWebUser(clientIP).tempPlayer4.nextTischSet = true;
                                    break;
                                case "down":
                                    Game.getWebUser(clientIP).tempPlayer4.steigtAuf = false;
                                    Game.getWebUser(clientIP).tempPlayer4.nextTischSet = true;
                            }
                        }
                        StringBuilder response = new StringBuilder();
                        response.append(Game.getWebUser(clientIP).tempPlayer1.getVorname()).append(" ").append(Game.getWebUser(clientIP).tempPlayer1.getNachname()).append(";").append(Game.getWebUser(clientIP).tempPlayer1.getPoints());
                        response.append(";").append(Game.getWebUser(clientIP).tempPlayer2.getVorname()).append(" ").append(Game.getWebUser(clientIP).tempPlayer2.getNachname()).append(";").append(Game.getWebUser(clientIP).tempPlayer2.getPoints());
                        response.append(";").append(Game.getWebUser(clientIP).tempPlayer3.getVorname()).append(" ").append(Game.getWebUser(clientIP).tempPlayer3.getNachname()).append(";").append(Game.getWebUser(clientIP).tempPlayer3.getPoints());
                        response.append(";").append(Game.getWebUser(clientIP).tempPlayer4.getVorname()).append(" ").append(Game.getWebUser(clientIP).tempPlayer4.getNachname()).append(";").append(Game.getWebUser(clientIP).tempPlayer4.getPoints());

                        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                        exchange.sendResponseHeaders(200, response.toString().getBytes().length);
                        exchange.getResponseBody().write(response.toString().getBytes());
                        exchange.getResponseBody().close();
                        break;
                    case "setPointsSucces":
                        System.out.println(Game.GREEN + "Punkte von " + Objects.requireNonNull(Game.getWebUser(clientIP)).getName() + " erfolgreich empfangen!" + Game.RESET);
                        Objects.requireNonNull(Game.getWebUser(clientIP)).addPlayerPoints();
                        break;
                    default:
                        System.out.println(Game.RED + "Unbekannte Anfrage vom Server! Bitte prüfen");
                        System.out.println(input.get(0)  + Game.RESET);
                }
            } else {
                System.out.println("Ungültige Anfrage-Methode: " + exchange.getRequestMethod());
            }
        }
    }

    static class SendTextOnExitHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
                exchange.sendResponseHeaders(204, -1);
                return;
            } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String text = br.readLine();
                String clientIP = exchange.getRemoteAddress().getAddress().getHostAddress();
                System.out.println(Game.getWebUser(clientIP).getName() + " hat die Seite verlassen!");
                Gui.disconnectWebUser(clientIP);

                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, -1); // Bestätigungsnachricht senden (kein Inhalt)
                exchange.getResponseBody().close();
            } else {
                System.out.println("Ungültige Anfrage-Methode: " + exchange.getRequestMethod());
            }
        }
    }
}
