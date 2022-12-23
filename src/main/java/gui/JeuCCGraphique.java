package src.main.java.gui;

import java.util.ArrayList;

import src.main.java.model.CC.CoteCC;
import src.main.java.model.CC.TuileCC;
import src.main.java.model.CC.ValeurCC;
import src.main.java.model.DC.PlateauDC;
import src.main.java.model.general.Jeu;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Plateau;
import src.main.java.model.general.Tuile;
import src.main.java.model.CC.Paysage.*;
import src.main.java.model.CC.TuileCC.Centre;
import src.main.java.model.CC.TuileCC.Centre.Abbaye;
import src.main.java.model.CC.TuileCC.Centre.Carrefour;

public class JeuCCGraphique extends Jeu {

    public JeuCCGraphique(){
		joueurs = new ArrayList<Joueur>();
		sac = new ArrayList<Tuile>();
		plateau = new PlateauDC();
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
            sac.add(tuile);
        }

        // 2 // Ville en haut, route à droite et en bas
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Mur(), new Ville(false), new Mur());
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 3 // Ville à gauche et en haut, route à droite et en bas, avec shield
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Ville(true), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Ville(true), new Ville(false), new Mur());
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 4 // Ville à gauche, en haut et à droite, route en bas
        for (int i = 0; i < 1; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Mur());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 5 // Pareil qu'en haut, mais avec un shield et sans route en bas
        for (int i = 0; i < 1; i++) {
            CoteCC gauche = new CoteCC(new Ville(true), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Mur());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 6 // Ville à gauche et en haut, route à droite et en bas, sans shield
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false),new Mur());
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 7 // Carrefour à 3 routes, ville en haut
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC haut = new CoteCC(new Mur(), new Ville(false), new Mur());
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            tuile.setCentre(new Carrefour());
            sac.add(tuile);
        }

        // 8 // Route vertical
        for (int i = 0; i < 8; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
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
            sac.add(tuile);
        }

        // 10 // Ville en haut, tout le reste pré
        for (int i = 0; i < 5; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Mur(),new Ville(false),new Mur());
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 11 // Ville en haut et à droite, tout le reste pré
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Pre());
            CoteCC haut = new CoteCC(new Mur(),new Ville(false),new Mur());
            CoteCC droite = new CoteCC(new Mur(),new Ville(false),new Mur());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 12 // Ville à gauche, en haut et à droite
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Mur());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
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
            sac.add(tuile);
        }

        // 15 // Ville à gauche et en haut
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Ville(false), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Ville(false), new Ville(false), new Mur());
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 16 // Ville en tunnel avec shield
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Mur(), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Mur(), new Ville(true), new Mur());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 17 // Route horizontal avec ville en haut
        for (int i = 0; i < 4; i++) {
            CoteCC gauche = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC haut = new CoteCC(new Mur(), new Ville(false), new Mur());
            CoteCC droite = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 18 // Route à gauche et en bas, avec ville en haut
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Pre(), new Route(), new Pre());
            CoteCC haut = new CoteCC(new Mur(), new Ville(false), new Mur());
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 19 // Ville en tunnel sans shield
        for (int i = 0; i < 1; i++) {
            CoteCC gauche = new CoteCC(new Mur(), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Mur(), new Ville(false), new Mur());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 20 // Ville à gauche, en haut et à droite, route en bas
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Ville(true), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Ville(true), new Ville(false), new Ville(false));
            CoteCC droite = new CoteCC(new Ville(false), new Ville(false), new Mur());
            CoteCC bas = new CoteCC(new Pre(), new Route(), new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 21 // Full ville
        for (int i = 0; i < 1; i++) {
            CoteCC gauche = new CoteCC(new Ville(true));
            CoteCC haut = new CoteCC(new Ville(true));
            CoteCC droite = new CoteCC(new Ville(false));
            CoteCC bas = new CoteCC(new Ville(false));
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
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
            sac.add(tuile);
        }

        // 23 // Ville à gauche et en haut, avec shield
        for (int i = 0; i < 2; i++) {
            CoteCC gauche = new CoteCC(new Ville(true), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Ville(true), new Ville(false), new Mur());
            CoteCC droite = new CoteCC(new Pre());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }

        // 24 // Ville à gauche et à droite
        for (int i = 0; i < 3; i++) {
            CoteCC gauche = new CoteCC(new Mur(), new Ville(false), new Mur());
            CoteCC haut = new CoteCC(new Pre());
            CoteCC droite = new CoteCC(new Mur(), new Ville(false), new Mur());
            CoteCC bas = new CoteCC(new Pre());
            TuileCC tuile = new TuileCC(haut, droite, bas, gauche);
            sac.add(tuile);
        }
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
