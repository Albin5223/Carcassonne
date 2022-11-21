package src.main.java.model.general;

public abstract class Plateau {

    protected Case[][] cases;
    protected int longueur;
    protected int hauteur;

    // Limite de taille pour un plateau, on peut la changer pour augmenter ou réduire
    protected final int limite = 30;

    // Constructeur : avec taille limite
    public Plateau(int longueur, int hauteur){
        if(longueur > limite){longueur = limite;}
        if(hauteur > limite){hauteur = limite;}

        this.longueur = longueur;
        this.hauteur = hauteur;
        this.cases = new Case[longueur][hauteur];

        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < hauteur; j++) {
                cases[i][j] = new Case();
            }
        }
    }

    // Constructeur : plateau carré
    public Plateau(int i){
        this(i, i);
    }

    // Constructeur : sans argument, on donne une taille de 10
    public Plateau(){
        this(10);
    }

    // Méthodes getteurs
    public Case getCase(int x, int y){return cases[x][y];}
    public int getLongueur(){return longueur;}
    public int getHauteur(){return hauteur;}
    
    // Méthodes setteurs
    public void setCase(Case c,int x, int y){cases[x][y] = c;}
    public void setPlateau(Case[][] p){cases = p;}


    public abstract void afficher();

}
