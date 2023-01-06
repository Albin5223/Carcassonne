package src.main.java.model.DC;

import src.main.java.model.general.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JeuDCShell extends Jeu {

    /*
     * lp : sert à définir le nombre de possibilité que peut avoir chaque case des côtés des tuiles.
     * Exemple : si lp = 3, les numéros des chiffres sur les tuiles iront de 1 jusqu'à 3
     * 
     * MINIMUM lp = 2
     */
    protected int lp = 3;

    // Attributs :
    public JeuDCShell(){
        joueurs = new ArrayList<Joueur>();
        sac = new ArrayList<Tuile>();
        plateau = new PlateauDC();
        tour = 0;
    }

    @Override
    public PlateauDC getPlateau(){
        return (PlateauDC) plateau;
    }

    public void lancerPartie() {

        // On initialise d'abord les joueurs
        setJoueur();

        // On initialise les tuiles ici
        initSac();

        // On initialise le plateau en plaçant juste la toute première tuile
        setPlateau();

        // On lance les tours ici
        jouerTour(); 
    }

    // Méthode pour demander le nombre de joueurs et les initialiser
    @SuppressWarnings("resource")
    public void setJoueur() {
        boolean boucle = true;
        int nombreHumain = 0;
        while (boucle){
            System.out.println("- Combien de joueurs participent ? : -");
            Scanner sc = new Scanner(System.in);
            try {                                   // Choix du nombre de joueurs (humain)
                nombreHumain = sc.nextInt();
                if (nombreHumain < 1 || nombreHumain > maxPlayers) {
                    System.err.println("- Rentrez un chiffre entre 1 et "+maxPlayers+" inclus : -");
                    boucle = true;
                } else {
                    boucle = false;
                }
            } catch (InputMismatchException e){
                System.err.println("- Rentrez un nombre valide. -");
                boucle = true;
            }
        }
        for (int i = 0; i < nombreHumain; i++) {
            System.out.println("- Quel est le nom du joueur n°" + (i+1) + " ? -");
            addJoueur(new Joueur(i));
        }

        boucle = true;
        int nombreOrdinateur = 0; 

        if(nombreHumain < maxPlayers){
            while (boucle){
                System.out.println("- Combien de bots voulez-vous ? : -");
                Scanner sc = new Scanner(System.in);
                try {                                   // Choix du nombre de joueurs (humain)
                    nombreOrdinateur = sc.nextInt();
                    if (nombreOrdinateur+nombreHumain < 2 || nombreOrdinateur+nombreHumain > maxPlayers) {
                        if(nombreHumain > 1){
                            System.err.println("- Rentrez un chiffre entre 0 et "+(maxPlayers-nombreHumain)+" inclus : -");
                        }
                        else{
                            System.err.println("- Rentrez un chiffre entre 1 et "+(maxPlayers-nombreHumain)+" inclus : -");
                        }
                        boucle = true;
                    } else {
                        boucle = false;
                    }
                } catch (InputMismatchException e){
                    System.err.println("- Rentrez un nombre valide. -");
                    boucle = true;
                }
            }
            for (int i = 0; i < nombreOrdinateur; i++) {
                joueurs.add(new Ordinateur(i,niveauOrdinateur,this));
            }
        }
    }

    //Cette fonction permet de piocher un domino aléatoire dans le sac
    public TuileDC piocher(Joueur j){
        // On choisis un nombre aléatoire pour piocher dans le sac
        Random r=new Random();
        int n = r.nextInt(sac.size());
        // On trouve la tuile piochée
        TuileDC piocher = (TuileDC)sac.get(n);
        // On attribue à la tuile son titulaire
        piocher.setTitulaire(j);
        // On l'enlève du sac
        sac.remove(n);
        // On le retourne
        return piocher;
    }

    public void setPlateau(){
        TuileDC t = piocher(null);
        try {
            plateau.setTuile(t, 0, 0);
        } catch (CasePleineException e) {
            System.out.println("...La première tuile d'initialisation n'a pas été placée...");
            e.printStackTrace();
        }
    }

    public void jouerTour() {
        while(joueurs.size() > 1) {        // On continue le jeu tant qu'il reste au moins 2 joueurs
            // On montre l'etat actuel du jeu en printant le plateau
            afficher();

            Joueur joueur = joueurs.get(tour);
            System.out.println(" /// C'est au tour de "+joueur.getNom()+" : \\\\\\\n");     // On dit à qui est le tour
            TuileDC pioche = piocher(joueur); 
            System.out.println("Voici la tuile piochée :");             // On affiche sa pioche
            pioche.afficher();                                          // On le fait piocher

            if(joueur.isBot()){
                if(((Ordinateur) joueur).jouerTour(pioche)){
                    System.out.println(joueur.getNom() + " a placé sa tuile");
                }
                else{
                    System.out.println(joueur.getNom() + " n'a pas reussi à placer sa tuile");
                }
                joueurSuivant();
            }
            else{
                demanderChoix(joueur, pioche);              // On lui demande ce qu'il veut faire
            }

            if(joueur.getScore() >= maxScore || sacVide()){
                joueurGagnant(joueur);
            }
        }

        joueurGagnant(joueurs.get(0));      // On applaudi le joueur gagnant
    }

    private void joueurGagnant(Joueur joueur){
        System.out.println("Etat du plateau final :");
        ((PlateauDC) plateau).afficher();
        System.out.println("\n/// "+joueur.getNom()+" remporte la partie avec un score total de : "+joueur.getScore()+" \\\\\\\n");
        System.exit(0);
    }

    // Fonction pour demander à l'utilisateur un choix
    @SuppressWarnings("resource")
    private void demanderChoix(Joueur joueur, TuileDC pioche){
        boolean boucle = true;
        while (boucle) {
            System.out.println("- Que voulez-vous faire ? : -\n");
            System.out.println("| R -> rotation");
            System.out.println("| P -> placer la tuile");
            System.out.println("| D -> défausser sa tuile");
            System.out.println("| A -> abandonner");
            Scanner sc = new Scanner(System.in);
            try {
                String choix = sc.next();
                choix = choix.toUpperCase();
                if(choix.length()>1 || (choix.charAt(0) != 'R' && choix.charAt(0) != 'P' && choix.charAt(0) != 'D'&& choix.charAt(0) != 'A')){
                    System.err.println("- Rentrez un choix valide (R / P / D / A). -");
                    boucle = true;
                }
                else{
                    switch (choix.charAt(0)) {
                        case 'R':
                            pioche.rotation();
                            afficher();
                            System.out.println("Voici l'état de votre tuile :");
                            pioche.afficher();
                            demanderChoix(joueur, pioche);
                            boucle = false;
                            break;
                        case 'P':
                            placer(joueur,pioche);
                            boucle = false;
                            break;
                        case 'D':
                            joueurSuivant();
                            boucle = false;
                            break;
                        case 'A':
                            abandonne(joueur);
                            boucle = false;
                            break;

                    }
                }
            } catch (InputMismatchException e){
                System.err.println("- Rentrez un choix valide (R / P / D / A). -");
                boucle = true;
            }
        }
    }
    
    @Override
    public boolean placer(Tuile t, int x, int y) {
        return false;
    }

    // Fonction pour demander à l'utilisateur s'il veut placer sa tuile
    @SuppressWarnings("resource")
    private void placer(Joueur joueur, TuileDC tuile){
        boolean boucle = true;
        while (boucle) {
            System.out.println("- Que voulez-vous faire ? : -\n");
            System.out.println("| C -> choisir les coordonnées");
            System.out.println("| R -> retour arrière");
            Scanner sc = new Scanner(System.in);
            try {
                String choix = sc.next();
                choix = choix.toUpperCase();
                if(choix.length()>1 || (choix.charAt(0) != 'C' && choix.charAt(0) != 'R')){
                    System.err.println("- Rentrez un choix valide (C / R). -");
                    boucle = true;
                }
                else{
                    switch (choix.charAt(0)) {
                        case 'C':
                            Coordonnee c = demanderCoordonnee();
                            try {
                                ((PlateauDC) plateau).poserTuile(tuile, c.getX(), c.getY());
                                if(aGagne(joueur)){             // On regarde si le joueur a gagné après avoir poser sa tuile
                                    joueurGagnant(joueur);
                                }
                                joueurSuivant();
                            } catch (ActionImpossibleException | CasePleineException | TitulaireAbsentException e) {
                                System.err.println("- Impossible de placer votre tuile ici -");
                                placer(joueur, tuile);
                            }
                            break;
                        case 'R':
                            demanderChoix(joueur, tuile);
                            break;
                    }
                    boucle = false;
                }
            } catch (InputMismatchException e){
                System.err.println("- Rentrez un choix valide (C / R). -");
                boucle = true;
            }
        }
    }

    // Fonction pour demander à l'utilisateur les coordonnées
    @SuppressWarnings("resource")
    private Coordonnee demanderCoordonnee(){
        boolean boucle = true;
        int x = 0;
        while (boucle) {
            System.out.println("- Donnez votre X :");
            Scanner sc = new Scanner(System.in);
            try {
                x = sc.nextInt();
                boucle = false;
            } catch (InputMismatchException e){
                System.err.println("- Rentrez un entier valide. -");
                boucle = true;
            }
        }

        boucle = true;
        int y = 0;
        while (boucle) {
            System.out.println("- Donnez votre Y :");
            Scanner sc = new Scanner(System.in);
            try {
                y = sc.nextInt();
                boucle = false;
            } catch (InputMismatchException e){
                System.err.println("- Rentrez un entier valide. -");
                boucle = true;
            }
        }
        return new Coordonnee(x, y);
    }

    private boolean aGagne(Joueur joueur){
        if(joueur.getScore()>= maxScore){
            return true;
        }
        return false;
    }

    // Fonction pour faire abandonner un joueur
    private void abandonne(Joueur joueur){
        joueur.abandonne();
        joueurs.remove(joueur);
        if(tour >= joueurs.size()-1){tour = 0;}
    }

    // Affichage du dominos
    public void afficher(){
        ((PlateauDC) plateau).afficher();
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
}