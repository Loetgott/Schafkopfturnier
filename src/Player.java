public class Player {
    String nachname;
    String vorname;
    int points;
    Tisch tisch;
    int roundPoints;
    public boolean hasRoundPoints = false;

    public Player(String vorname, String nachname) {
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public String[] getName() {

        return new String[]{vorname, nachname};
    }

    public String getVorname(){
        return vorname;
    }

    public String getNachname(){
        return nachname;
    }

    public void setName(String vorname, String nachname) {
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public int getPoints() {
        return points + roundPoints;
    }
    public int getOldPoints(){
        return points;
    }
    public int getRoundPoints(){
        return roundPoints;
    }

    public void setPoints(int points) {
        hasRoundPoints = true;
        this.points = points;
    }
    public void setTisch(Tisch nTisch){
        this.tisch = nTisch;
    }
    public Tisch getTisch(){
        return this.tisch;
    }
    public void addRoundPoints(int nRoundPoints){
        hasRoundPoints = true;
        roundPoints = roundPoints + nRoundPoints;

    }
    public void nextRound(){
        hasRoundPoints = false;
        points = points + roundPoints;
        roundPoints = 0;
    }
}
