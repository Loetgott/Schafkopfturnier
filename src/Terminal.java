import java.util.Scanner;

public class Terminal{
    private Scanner scanner;
    public Terminal() {
        this.scanner = new Scanner(System.in);
    }

    public void checkForInput() {
        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            run(userInput);
        }
    }

    public void run(String input){

        String[] parts = input.split(" ");
        String command = parts[0];


        switch(command){
            case "info":
                System.out.println("hier müssen alle befehle eingetragen werden, damit das hier das info menü ist.");
                break;

            case "create" : //Spieler erstellen

                String playerName = "";

                if(parts.length == 2){
                    playerName = parts[1];
                } else if (parts.length > 2) {
                    playerName = parts[1] + " " + parts[2];
                }

                if(playerName.isEmpty()){
                    System.out.println("Man, gib doch den Namen ein!!!");
                }else{
                    System.out.println("Spieler "+playerName+" erstellt!");
                    Player newPlayer = new Player(playerName);
                }
                break;




            default:
                System.out.println("Ungültiger Befehl. Unter 'info' finden sie alle Befehle");
        }
    }
}