public class Player {
    String name;
    String vorname;
    int points;

    public Player(String name, String vorname) {
        this.name = name;
        this.vorname = vorname;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
