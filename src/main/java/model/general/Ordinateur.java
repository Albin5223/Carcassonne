package src.main.java.model.general;

public class Ordinateur extends Joueur {

    protected Strategie strategie;

    public Ordinateur(int id, int strat, Jeu jeu) {
        super("Ordinateur "+id, id);
        ordinateur = true;
        choixStategie(strat);
        strategie.setJeu(jeu);
    }

    private void choixStategie(int choix){
        switch (choix) {
            default:
                strategie = new Strategie1();
                break;
            case 2:
                strategie = new Strategie2();
                break;
        }
    }

    public boolean jouerTour(Tuile tuile){
        return strategie.jouerTour(tuile);
    }

    /*
     * Il faudrait créer différent type de stratégie pour le bot assez simple du style :
     * 
     * BOT LVL 1 : Parcours les possibilités de placement, 
     * et pose sa tuile à la première place trouvée (sans faire de rotation de tuile parce qu'il est bête)
     * 
     * BOT LVL 2 : Parcours les possibilités de placement (avec rotation) et place à la première place trouvée
     * 
     * BOT LVL 3 : Parcours TOUTES les possibilités de placement (avec rotation),
     * et choisit l'emplacement qui lui rapporte le + de points (pas con ce petit bot)
     * 
     * -----------------------------------
     * 
     * Et pour mettre tout ça en place, on pourrait peut-être créer une classe du style "Strategie.java" et jsp faire des choses dedans (à voir)
     */
    
}
