import java.util.ArrayList;

public class Player implements Comparable<Player> {
    String nachname;
    String vorname;
    int points;
    Tisch tisch;
    int roundPoints;
    public boolean hasRoundPoints = false;
    public boolean steigtAuf;
    public boolean nextTischSet;

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
        return points;
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
        nextTischSet = false;
        steigtAuf = false;
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
        if(steigtAuf){
            if(Game.tischList.indexOf(tisch) != Game.tischList.size() - 1){
                tisch = Game.tischList.get(Game.tischList.indexOf(tisch) + 1);
            }else{
                tisch = Game.tischList.get(0);
            }
        }else{
            if(Game.tischList.indexOf(tisch) != 0){
                tisch = Game.tischList.get(Game.tischList.indexOf(tisch) - 1);
            }else{
                tisch = Game.tischList.get(Game.tischList.size() - 1);
            }
        }
        nextTischSet = false;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
    }

    public boolean isHasRoundPoints() {
        return hasRoundPoints;
    }

    public void setHasRoundPoints(boolean hasRoundPoints) {
        this.hasRoundPoints = hasRoundPoints;
    }

    public boolean isSteigtAuf() {
        return steigtAuf;
    }

    public boolean isNextTischSet() {
        return nextTischSet;
    }

    public void setNextTischSet(boolean nextTischSet) {
        this.nextTischSet = nextTischSet;
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.roundPoints, other.roundPoints);
    }


}
