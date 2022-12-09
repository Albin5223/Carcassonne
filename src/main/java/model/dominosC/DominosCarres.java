package src.main.java.model.dominosC;

import src.main.java.model.general.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
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

        // On lance les tours ici
        // TODO :

        
    }

    // Méthode pour demander le nombre de joueurs et les initialiser
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
            sc.close();
        }
        for (int i = 0; i < joueurs.length; i++) {
            System.out.println("- Quel est le nom du joueur n°" + (i+1) + " ? -");
            joueurs[i] = new Joueur(i);
        }
    }

    // Cette fonction permet de piocher un domino aléatoire dans le sac
    public TuileDominosCarres piocher(Joueur j){
        // On choisis un nombre aléatoire pour piocher dans le sac
        Random r=new Random();
        int n = r.nextInt(sac.size());

        // On trouve la tuile piochée
        TuileDominosCarres piocher = sac.get(n);
        // On attribue à la tuile son titulaire
        piocher.setTitulaire(j);
        // On l'enlève du sac
        sac.remove(n);
        // On le retourne
        return piocher;
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
        System.out.println(plateau);
    }

}