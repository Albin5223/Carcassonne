package src.main.java.model.general;
public class Case {
    
    // Attributs
    private boolean occupee;
    private Tuile tuile;

    // Constructeur
    public Case(){
        occupee = false;
        tuile = null;
    }

    // Méthodes getteurs
    public boolean isOccupee(){return occupee;}
    public Tuile getTuile(){return tuile;}

    // Méthode pour poser une tuile sur la case
    public boolean poserTuile(Tuile tuile){
        if(occupee){return false;}
        this.tuile = tuile;
        occupee = true;
        return true;
    }
}
