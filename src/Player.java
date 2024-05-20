public class Player implements Comparable<Player> {
    String nachname;
    String vorname;
    int points;
    Tisch tisch;
    int roundPoints;
    public boolean hasRoundPoints = false;
    public boolean steigtAuf;

    public Player(String vorname, String nachname) {
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public String[] getName() {

        return new String[]{vorname, nachname};
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setName(String vorname, String nachname) {
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public int getPoints() {
        return points + roundPoints;
    }

    public int getOldPoints() {
        return points;
    }

    public int getRoundPoints() {
        return roundPoints;
    }

    public boolean getSteigtAuf() {
        return steigtAuf;}

    public void setPoints(int points) {
        this.points = points;}

    public void setTisch(Tisch nTisch) {
        this.tisch = nTisch;
    }

    public Tisch getTisch() {
        return this.tisch;
    }

    public void addRoundPoints(int nRoundPoints) {
        //System.out.println(vorname + " " + nachname + " hat " + nRoundPoints + " bekommen!");
        hasRoundPoints = true;
        roundPoints = roundPoints + nRoundPoints;

    }

    public void setSteigtAuf(boolean x) {
        steigtAuf = x;
    }

    public void nextRound(){
        hasRoundPoints = false;
        points = points + roundPoints;
        roundPoints = 0;
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.roundPoints, other.roundPoints);
    }


}
