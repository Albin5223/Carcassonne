package src.main.java.gui;

import java.util.ArrayList;

import src.main.java.model.DC.PlateauDC;
import src.main.java.model.general.Jeu;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Plateau;
import src.main.java.model.general.Tuile;

public class JeuCCGraphique extends Jeu {

    public JeuCCGraphique(){
		joueurs = new ArrayList<Joueur>();
		sac = new ArrayList<Tuile>();
		plateau = new PlateauDC();
		tour = 0;
		maxScore=60;
	}

    public void initSac(){

    }

    @Override
    public void lancerPartie() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setJoueur() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void jouerTour() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Plateau getPlateau() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
