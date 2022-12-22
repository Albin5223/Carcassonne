package src.main.java.model.CC;

import src.main.java.model.general.Valeur;

public class ValeurCC extends Valeur {

    public ValeurCC(Paysage paysage){
        this.valeur = paysage;
        this.points = paysage.points();
    }

    @Override
    public Paysage getValeur() {
        return (Paysage) valeur;
    }

    @Override
    public int getPoints() {
        return points;
    }   
}
