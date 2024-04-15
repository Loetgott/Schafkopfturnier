public class Main {
    public static void main(String[] args) {
        System.out.println("running the Program...");
        //TODO : Auslosen der Spieler am anfang zu den Tischen
        Gui gui = new Gui();
        Terminal terminal = new Terminal();
        while(true){
            terminal.checkForInput();
        }
    }
}
