import java.util.ArrayList;
import java.util.Arrays;
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
        String[] input1 =  userInput.split(" "); //aufspalten der wörter
        ArrayList<String> input = new ArrayList<>(Arrays.asList(input1));//machen zu einer arraylist
        switch(input.get(0)){
            case "info":
                System.out.println("addplayer <name>");
                break;
            case "addplayer":
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
            default:
                System.out.println("unbekannter befehl! bitte auf Rechtschreibung achten");
        }
    }
}