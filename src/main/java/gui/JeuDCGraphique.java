package src.main.java.gui;

import java.util.ArrayList;
import java.util.Random;

import src.main.java.model.DC.CoteDC;
import src.main.java.model.DC.PlateauDC;
import src.main.java.model.DC.TuileDC;
import src.main.java.model.DC.ValeurDC;
import src.main.java.model.general.CasePleineException;
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
		creerSac();
	}
	
	public Joueur getCurrentJoueur() {
		return joueurs.get(tour);
	}
	
	public void joueurSuivant() {
		tour+=1;
		if (tour == joueurs.size()) {
			tour=0;
		}
	}
	
	public Tuile setPlateau(){
        TuileDC t = piocher(null);
        try {
            plateau.setTuile(t, 0, 0);
            return t;
            
        } catch (CasePleineException e) {
            System.out.println("...La première tuile d'initialisation n'a pas été placée...");
            e.printStackTrace();
            return null;
        }
    }
	
	//Fonction qui initialise le sac avec tous les dominos possibles
	public void creerSac() {
    	ArrayList<CoteDC> pieces = new ArrayList<CoteDC>();
    	
    	for (int i = 1;i<4;i++) {
    		for (int j = 1;j<4;j++) {
    			for (int k = 1;k<4;k++) {
    				pieces.add(new CoteDC(new ValeurDC(i),new ValeurDC(j),new ValeurDC(k)));
    	    	}
        	}
    	}
    	
    	for (int i = 0;i<pieces.size();i++) {
    		for (int j = 0;j<pieces.size();j++) {
    			for (int k = 0;k<pieces.size();k++) {
    				for (int l = 0;l<pieces.size();l++) {
        				sac.add(new TuileDC(pieces.get(i),pieces.get(j),pieces.get(k),pieces.get(l)));
        	    	}
    	    	}
        	}
    	}
    }

    
  //Cette fonction permet de piocher un domino aléatoire dans le sac
    public TuileDC piocher(Joueur j){
        Random r=new Random();
        int n = r.nextInt(0,sac.size());
        TuileDC piocher = (TuileDC)sac.get(n);
        piocher.setTitulaire(j);
       
        sac.remove(n);
        
        return piocher;
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
