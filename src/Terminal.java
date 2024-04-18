import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Terminal{
    private Scanner scanner;
    public static Game game = new Game();
    public Terminal() {
        this.scanner = new Scanner(System.in);
    }

    public void checkForInput() {
        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            run(userInput);
        }
    }

    public void run(String userInput){
        String[] input1 = userInput.split(" "); //aufspalten der wörter
        ArrayList<String> input = new ArrayList<>(Arrays.asList(input1));//machen zu einer arraylist
        switch(input.get(0)){
            case "info":
                System.out.println("spielerhinzufügen <name>");
                break;

            case "spielerhinzufügen":
                input.remove(0);
                if(!input.isEmpty()){
                    String vorname = input.get(0);
                    input.remove(0);
                    if(!input.isEmpty()){
                        String nachname = input.get(0);
                        game.addPlayer(new Player(vorname,nachname));
                    }
                }
                break;
            case "updateleaderboard":
                System.out.println("playerList vor Tausch" + Game.playerList.size());
                Game.spielerZuordnen();
                System.out.println(" Tisch list nach atsuch" + Game.tischList.size());
                break;

            case "updatetisch":
                Game.updateTisch();
                break;

            case "tischezuteilen":
                Game.spielerZuordnen();
                break;

            case "spielertauschen":
                input.remove(0);
                if(Game.sucheSpieler( input.get(0) , input.get(1))==null || Game.sucheSpieler( input.get(2) , input.get(3))==null){
                    System.out.println("Ein Name wird nicht gefunden. Bitte auf Rechtschreibung achten!");
                }else{
                    Game.spielertausch(Objects.requireNonNull(Game.sucheSpieler(input.get(0), input.get(1))), Objects.requireNonNull(Game.sucheSpieler(input.get(2), input.get(3))));
                }
                break;

            default:
                System.out.println("unbekannter befehl! bitte auf Rechtschreibung achten");
        }
    }
}