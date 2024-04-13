public class Player {
    String nachname;
    String vorname;
    int points;

    public Player(String vorname, String nachname) {
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public String[] getName() {

        return new String[]{vorname, nachname};
    }

    public void setName(String vorname, String nachname) {
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
