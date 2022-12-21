package src.main.java.gui;

import java.util.ArrayList;

import src.main.java.model.DC.PlateauDC;
import src.main.java.model.general.Jeu;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Plateau;
import src.main.java.model.general.Tuile;

public class JeuDCGraphique extends Jeu {

	int tour;
	
	public JeuDCGraphique(){
		joueurs = new ArrayList<Joueur>();
		sac = new ArrayList<Tuile>();
		plateau = new PlateauDC();
		tour = 0;
	}
	
	public void addJoueur(Joueur j) {
		joueurs.add(j);
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
