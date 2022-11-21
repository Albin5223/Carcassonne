package src.main.java.model;
public abstract class Plateau {

    protected Case[][] cases;
    protected int longueur;
    protected int largeur;

    // Limite de taille pour un plateau, on peut la changer pour augmenter ou réduire
    protected final int limite = 10;

    // Constructeur : avec taille limite
    public Plateau(int longueur, int largeur){
        if(longueur > limite){longueur = limite;}
        if(largeur > limite){largeur = limite;}

        this.longueur = longueur;
        this.largeur = largeur;
        this.cases = new Case[longueur][largeur];

        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < largeur; j++) {
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
    public int getLargeur(){return largeur;}
    
    // Méthodes setteurs
    public void setCase(Case c,int x, int y){cases[x][y] = c;}
    public void setPlateau(Case[][] p){cases = p;}


    public abstract void afficher();

}
