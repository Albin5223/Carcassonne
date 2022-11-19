package src.main.java.model;
public class TuileDominosCarees extends Tuile {

    // Attributs
    private int taille;
    private int[] haut;
    private int[] droite;
    private int[] bas;
    private int[] gauche;

    // Constructeur : pour le moment, on force les tuiles à avoir des cotés avec 3 valeurs, on pourra potentiellement augmenter la taille après
    public TuileDominosCarees(int taille, int[] haut, int[] droite, int[] bas, int[] gauche) throws Exception {
        if(haut.length != taille || droite.length != taille || bas.length != taille || gauche.length != taille){
            throw new Exception("Les dimensions de la tuile ne sont pas correctes");
        }
        else{
            this.taille = taille;
            this.haut = new int[taille]; this.droite = new int[taille]; this.bas = new int[taille]; this.gauche = new int[taille];
            for(int i = 0; i<taille;i++){
                this.haut[i] = haut[i];
                this.droite[i] = droite[i];
                this.bas[i] = bas[i];
                this.gauche[i] = gauche[i];
            }
        }
    }

}
