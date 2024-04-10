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
        switch(input){
            case "info":
                System.out.println("hier müssen alle befehöe eingetragen werden, damit das hier das info menü ist.");
                break;
        }
    }
}