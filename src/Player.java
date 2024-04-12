public class Player {
    String name;
    String vorname;
    int points;
    int nPoints;

    public Player(String name, String vorname) {
        this.name = name;
        this.vorname = vorname;
    }

    public String getName() {

        return name + " " + vorname;
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

    public int getnPoints() {
        return nPoints;
    }

    public void setnPoints(int nPoints) {
        this.nPoints = nPoints;
    }
}
