package src.main.java.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DominosCarres extends Jeu {

    // Attributs :
    // Cet attribut représente toutes les tuiles qui exisent dans notre jeu
    private ArrayList <TuileDominosCarres> tuiles = new ArrayList<TuileDominosCarres>();
    // Cet attribut représente les tuiles stockées dans le sac du jeu
    private ArrayList <TuileDominosCarres> sac = new ArrayList<TuileDominosCarres>();

    @Override
    public void lancerPartie() {

        // On initialise d'abord les joueurs
        setJoueur();

        // On initialise le plateau
        // TODO :

        // On initialise les tuiles ici
        // TODO :

        // On distribue les tuiles ici
        // TODO :

        
    }

    @Override
    public void setJoueur() {
        boolean boucle = true;
        while (boucle){
            System.out.println("- Combien de joueurs participent ? : -");
            Scanner sc = new Scanner(System.in);
            int nbrJ = 1;
            try {
                nbrJ = sc.nextInt();
                if (nbrJ < 2 || nbrJ > 4) {
                    System.err.println("- Rentrez un chiffre entre 2 et 4 inclus : -");
                    boucle = true;
                } else {
                    this.joueurs = new Joueur[nbrJ];
                    boucle = false;
                }
            } catch (InputMismatchException e){
                System.err.println("- Rentrez un nombre valide. -");
                boucle = true;
            }
        }
        for (int i = 0; i < joueurs.length; i++) {
            System.out.println("- Quel est le nom du joueur n°" + (i+1) + " ? -");
            joueurs[i] = new Joueur(i);
        }
    }

    @Override
    public void jouerTour() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void abandonner(Joueur j) {
        // TODO Auto-generated method stub
        
    }

    // Affichage du dominos
    public void afficher(){
        plateau.afficher();
    }

}