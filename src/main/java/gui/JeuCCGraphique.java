package src.main.java.gui;

import java.util.ArrayList;
import java.util.Random;

import src.main.java.model.CC.CoteCC;
import src.main.java.model.CC.TuileCC;
import src.main.java.model.DC.ActionImpossibleException;
import src.main.java.model.general.CasePleineException;
import src.main.java.model.general.Jeu;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.TitulaireAbsentException;
import src.main.java.model.general.Tuile;
import src.main.java.model.CC.Paysage.*;
import src.main.java.model.CC.PlateauCC;
import src.main.java.model.CC.TuileCC.Centre.Abbaye;
import src.main.java.model.CC.TuileCC.Centre.Carrefour;

public class JeuCCGraphique extends Jeu {

    public JeuCCGraphique(){
		joueurs = new ArrayList<Joueur>();
		sac = new ArrayList<Tuile>();
		plateau = new PlateauCC();
		tour = 0;
		maxScore=60;
	}

    public void initSac(){

        // 1 // Route à gauche et en bas, avec pré tout autour
        for (int i = 0; i < 9; i++) {
            CoteCC gauche = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile1.png");
            sac.add(tuile);
        }

        // 2 // Ville en haut, route à droite et en bas
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile2.png");
            sac.add(tuile);
        }

        // 3 // Ville à gauche et en haut, route à droite et en bas, avec shield
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile3.png");
            sac.add(tuile);
        }

        // 4 // Ville à gauche, en haut et à droite, route en bas
        for (int i = 0; i < 1; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile4.png");
            sac.add(tuile);
        }

        // 5 // Pareil qu'en haut, mais avec un shield et sans route en bas
        for (int i = 0; i < 1; i++) {
            CoteCC gauche = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile5.png");
            sac.add(tuile);
        }

        // 6 // Ville à gauche et en haut, route à droite et en bas, sans shield
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false),new Ville(false));
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile6.png");
            sac.add(tuile);
        }

        // 7 // Carrefour à 3 routes, ville en haut
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setCentre(new Carrefour());
            tuile.setName("tuile7.png");
            sac.add(tuile);
        }

        // 8 // Route vertical
        for (int i = 0; i < 8; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile8.png");
            sac.add(tuile);
        }

        // 9 // Carrefour à 3 routes
        for (int i = 0; i < 4; i++) {
            CoteCC gauche = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setCentre(new Carrefour());
            tuile.setName("tuile9.png");
            sac.add(tuile);
        }

        // 10 // Ville en haut, tout le reste pré
        for (int i = 0; i < 5; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Ville(false),new Ville(false),new Ville(false));
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile10.png");
            sac.add(tuile);
        }

        // 11 // Ville en haut et à droite, tout le reste pré
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Ville(false),new Ville(false),new Ville(false));
            CoteCC droite = new CoteCC(new Ville(false),new Ville(false),new Ville(false));
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile11.png");
            sac.add(tuile);
        }

        // 12 // Ville à gauche, en haut et à droite
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile12.png");
            sac.add(tuile);
        }

        // 13 // Abbaye entouré de pré
        for (int i = 0; i < 4; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setCentre(new Abbaye());
            tuile.setName("tuile13.png");
            sac.add(tuile);
        }

        // 14 // Abbaye avec route en bas
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(),new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setCentre(new Abbaye());
            tuile.setName("tuile14.png");
            sac.add(tuile);
        }

        // 15 // Ville à gauche et en haut
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile15.png");
            sac.add(tuile);
        }

        // 16 // Ville en tunnel avec shield
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Ville(false), new Ville(true), new Ville(false));
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile16.png");
            sac.add(tuile);
        }

        // 17 // Route horizontal avec ville en haut
        for (int i = 0; i < 4; i++) {
            CoteCC gauche = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile17.png");
            sac.add(tuile);
        }

        // 18 // Route à gauche et en bas, avec ville en haut
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile18.png");
            sac.add(tuile);
        }

        // 19 // Ville en tunnel sans shield
        for (int i = 0; i < 1; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile19.png");
            sac.add(tuile);
        }

        // 20 // Ville à gauche, en haut et à droite, route en bas
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile20.png");
            sac.add(tuile);
        }

        // 21 // Full ville
        for (int i = 0; i < 1; i++) {
            CoteCC gauche = new CoteCC(new Ville(true));
            CoteCC haut = new CoteCC(new Ville(true));
            CoteCC droite = new CoteCC(new Ville(false));
            CoteCC bas = new CoteCC(new Ville(false));
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile21.png");
            sac.add(tuile);
        }

        // 22 // Carrefour à 4 routes
        for (int i = 0; i < 1; i++) {
            CoteCC gauche = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC haut = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setCentre(new Carrefour());
            tuile.setName("tuile22.png");
            sac.add(tuile);
        }

        // 23 // Ville à gauche et en haut, avec shield
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile23.png");
            sac.add(tuile);
        }

        // 24 // Ville à gauche et à droite
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setName("tuile24.png");
            sac.add(tuile);
        }
    }


    public void addJoueur(Joueur joueur) {
		joueurs.add(joueur);
	}

    public void lancerPartie() {
        initSac();
    }
    
    public boolean placer(Tuile t,int x, int y) {
		try {
			plateau.poserTuile((TuileCC) t, x, y);
			return true;
		} catch (ActionImpossibleException | CasePleineException | TitulaireAbsentException e) {
			return false;
		}
	}

    public Tuile setPlateau(){
        TuileCC t = piocher(null);
        try {
            plateau.setTuile(t, 0, 0);
            return t;
            
        } catch (CasePleineException e) {
            System.out.println("...La première tuile d'initialisation n'a pas été placée...");
            e.printStackTrace();
            return null;
        }
    }
    
    public TuileCC piocher(Joueur j){
        Random r=new Random();
        int n = r.nextInt(0,sac.size());
        TuileCC piocher = (TuileCC) sac.get(n);
        piocher.setTitulaire(j);
       
        sac.remove(n);
        
        return piocher;
    }

    @Override
    public PlateauCC getPlateau() {
        return (PlateauCC) plateau;
    }
    
}
