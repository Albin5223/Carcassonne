package src.main.java.model.DC;

import src.main.java.model.general.Valeur;

public class ValeurDC extends Valeur {

    public ValeurDC(int valeur){
        this.valeur = valeur;
        this.points = valeur;
    }

    @Override
    public Integer getValeur() {
        return (Integer) valeur;
    }

    @Override
    public int getPoints() {
        return points;
    }    
}
