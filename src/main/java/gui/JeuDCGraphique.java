package src.main.java.gui;

import java.util.ArrayList;
import java.util.Random;

import src.main.java.model.DC.ActionImpossibleException;
import src.main.java.model.DC.CoteDC;
import src.main.java.model.DC.PlateauDC;
import src.main.java.model.DC.TuileDC;
import src.main.java.model.general.CasePleineException;
import src.main.java.model.general.Jeu;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.TitulaireAbsentException;
import src.main.java.model.general.Tuile;

public class JeuDCGraphique extends Jeu {

	/*
     * lp : sert à définir le nombre de possibilité que peut avoir chaque case des côtés des tuiles.
     * Exemple : si lp = 3, les numéros des chiffres sur les tuiles iront de 1 jusqu'à 3
	 * 
	 * MINIMUM lp = 2
     */
    protected int lp = 2;

	public JeuDCGraphique(){
		joueurs = new ArrayList<Joueur>();
		sac = new ArrayList<Tuile>();
		plateau = new PlateauDC();
		tour = 0;
		maxScore=60;
	}

	//Fonction qui initialise le sac avec tous les dominos possibles
	public void initSac() {
		ArrayList<CoteDC> pieces = new ArrayList<CoteDC>();
		
		if(lp <= 0){lp = 2;}    // Au cas où

		for (int i = 1;i<=lp;i++) {
			for (int j = 1;j<=lp;j++) {
				for (int k = 1;k<=lp;k++) {
					pieces.add(new CoteDC(i,j,k));
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

	public void lancerPartie() {
		initSac();
	}
	
	public boolean placer(Tuile t,int x, int y) {
		try {
			plateau.poserTuile((TuileDC)t, x, y);
			return true;
		} catch (ActionImpossibleException | CasePleineException | TitulaireAbsentException e) {
			return false;
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
	public PlateauDC getPlateau() {
		return (PlateauDC) plateau;
	}
}
