package src.main.java.model.general;

public abstract class Plateau {

    protected Case[][] cases;
    protected int hauteur;
    protected int longueur;

    // Limite de taille pour un plateau, on peut la changer pour augmenter ou réduire
    protected final int limite = 30;

    // Constructeur : avec taille limite
    public Plateau(int hauteur, int longueur){
        if(hauteur > limite){hauteur = limite;}
        if(longueur > limite){longueur = limite;}

        this.hauteur = hauteur;
        this.longueur = longueur;
        this.cases = new Case[hauteur][longueur];

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < longueur; j++) {
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
    public int getHauteur(){return hauteur;}
    public int getLongueur(){return longueur;}
    
    // Méthodes setteurs
    public void setCase(Case c,int x, int y){cases[x][y] = c;}
    public void setPlateau(Case[][] p){cases = p;}


    public abstract void afficher();

}
