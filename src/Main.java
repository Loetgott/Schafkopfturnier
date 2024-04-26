public class Main {
    public static void main(String[] args) {
        System.out.println("running the Program...");
        Gui gui = new Gui();
        Terminal terminal = new Terminal();
        while(true){
            terminal.checkForInput();
        }
    }
}
