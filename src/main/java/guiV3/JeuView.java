package src.main.java.guiV3;

import src.main.java.model.DC.TuileDC;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Ordinateur;
import src.main.java.model.general.Tuile;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.awt.*;

public class JeuView extends JFrame {
    
    PlateauView plateau;
    InformationsView information;

    JeuDCGraphique jeu;
    
    public JeuView(){

        jeu = new JeuDCGraphique();
        this.setPreferredSize(new Dimension(600,400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        
        plateau = new PlateauView();
        Joueur j = new Joueur("Servan", 1);
        Joueur j2 = new Joueur("Frrraac", 2);
        jeu.addJoueur(j);
        jeu.addJoueur(j2);
        information = new InformationsView();

        miseEnForme(plateau, 0, 0, 3, 1);
        miseEnForme(information, 1, 0, 1, 1);

        initJeu();
    }

    public void miseEnForme(JPanel panel,int gridx, int gridy, double weightx, double weighty){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = gridx;
        c.gridy = gridy;
        c.weightx = weightx;
        c.weighty = weighty;
        this.add(panel,c);
    }

    public void initJeu() {   
        jeu.lancerPartie();
        Tuile t = ((JeuDCGraphique) jeu).setPlateau();
        placerTuile(t,0,0);
	}
	
	public void placerTuile(Tuile t,int x,int y) {
		TuileDCView t1 = new TuileDCView((TuileDC)t,plateau);
		plateau.setTuile(t1,x,y);
	}

    public class InformationsView extends JPanel {
		
        TuileDCView t1;
        TuileDC tuile;
        JButton piocher;
        JButton placer;
        JButton defausser;
        JButton tourner;
        JButton suivant;
        JButton abandonner;
        JoueurView jv1;
        
        JPanel panneauButton;
        JLabel message;
        
        public InformationsView() {
            this.setLayout(new GridLayout(3,1));
            
            jv1 = new JoueurView(jeu.getCurrentJoueur());
            panneauButton = new JPanel();
            panneauButton.setLayout(new GridLayout(7,1));

            panneauButton.setBackground(Color.BLUE);
            
            piocher = new JButton("Piocher");
            panneauButton.add(piocher);
            
            piocher.addActionListener((ActionEvent e) ->{
                piocher();
            });
            
            placer = new JButton("Placer");
            placer.setEnabled(false);
            placer.addActionListener((ActionEvent e) ->{
                placer();
            });
            panneauButton.add(placer);
            
            suivant = new JButton("Suivant");
            suivant.setEnabled(false);
            suivant.addActionListener((ActionEvent e) ->{
                if (jeu.partieFinie()) {
                    jv1.annonceVainqueur();
                    piocher.setEnabled(false);
                    placer.setEnabled(false);
                    defausser.setEnabled(false);
                    tourner.setEnabled(false);
                    suivant.setEnabled(false);
                    abandonner.setEnabled(false);
                    quitter();
                }
                else {
                    suivant();
                }
            });
            panneauButton.add(suivant);
            
            defausser = new JButton("Defausser");
            defausser.setEnabled(false);
            defausser.addActionListener((ActionEvent e) ->{
                defausser();
                
            });
            panneauButton.add(defausser);
            
            tourner = new JButton("Rotation");
            tourner.setEnabled(false);
            tourner.addActionListener((ActionEvent e) ->{
                this.remove(t1);
                tuile.rotation();
                t1 = new TuileDCView((TuileDC) tuile,plateau);
                this.add(t1);
                this.setVisible(false);
                this.setVisible(true);
            });
            panneauButton.add(tourner);
            
              
            abandonner = new JButton("Abandonner");
            panneauButton.add(abandonner);
            abandonner.addActionListener((ActionEvent e) ->{
                jeu.abandonner();
                if(t1 != null) {
                    defausser();
                }
                
                if (jeu.partieFinie()) {
                    jv1.annonceVainqueur();
                    piocher.setEnabled(false);
                    placer.setEnabled(false);
                    defausser.setEnabled(false);
                    tourner.setEnabled(false);
                    suivant.setEnabled(false);
                    abandonner.setEnabled(false);
                    quitter();
                }
                else {
                    suivant();
                }
                
            });
            
            this.add(jv1);
            this.add(panneauButton);
            
            message = new JLabel();
            message.setHorizontalAlignment(JLabel.CENTER);
            panneauButton.add(message);
            
        }
        
        public void defausser() {
            this.remove(t1);
            this.setVisible(false);
            this.setVisible(true);
            tourner.setEnabled(false);
            suivant.setEnabled(true);
            placer.setEnabled(false);
            defausser.setEnabled(false);
        }
        
        public void quitter() {
            this.addMouseListener(new MouseListener() {
    
                @Override
                public void mouseClicked(MouseEvent e) {
                    JeuView.this.dispose();
                }
    
                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
    
                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
    
                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
    
                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
                
            });
        }
        
        public void suivant() {
            jeu.joueurSuivant();
            if (jeu.partieFinie()) {
                jv1.annonceVainqueur();
                piocher.setEnabled(false);
                placer.setEnabled(false);
                defausser.setEnabled(false);
                tourner.setEnabled(false);
                suivant.setEnabled(false);
                abandonner.setEnabled(false);
                quitter();
                return;
            }
            jv1.replace(jeu.getCurrentJoueur());
            piocher.setEnabled(true);
            placer.setEnabled(false);
            abandonner.setEnabled(true);
            suivant.setEnabled(false);
            if (message!=null) {
                panneauButton.remove(message);
            }
            if (jeu.getCurrentJoueur().isBot()) {
                Tuile tuileOrdi = jeu.piocher(jeu.getCurrentJoueur());
                Joueur j = jeu.getCurrentJoueur();
                if (((Ordinateur)j).jouerTour(tuileOrdi)) {
                    JeuView.this.placerTuile(tuileOrdi,tuileOrdi.getCoordonnee().getX(),tuileOrdi.getCoordonnee().getY());
                }
                suivant();
            }
        }
            
        public void placer() {
            int x = 0;
            int y = 0;
            if (tuile != null) {
                try {
                }
                catch(Exception e){
                    
                }
                
                if (jeu.placer(tuile,x, y)){
                    placerTuile(tuile, x, y);
                    plateau.repaint();
                    message.setText("Bien joue");
                    message.setForeground(Color.WHITE );
                    this.repaint();
                    this.remove(t1);
                    defausser.setEnabled(false);
                    tourner.setEnabled(false);
                    placer.setEnabled(false);
                    abandonner.setEnabled(false);
                    suivant.setEnabled(true);
                    jv1.refresh();
                    
                    this.setVisible(false);
                    this.setVisible(true);
                    }
                else {
                    message.setText("Erreur dans le placement");
                    message.setForeground(Color.RED);
                    this.repaint();
                }
            }		
        }
        
        public void piocher() {
            tuile = jeu.piocher(jeu.getCurrentJoueur());
            t1 = new TuileDCView(tuile,plateau);

            this.add(t1);
            this.repaint();
            t1.setVisible(false);
            t1.setVisible(true);
            tourner.setEnabled(true);
            defausser.setEnabled(true);
            placer.setEnabled(true);
            piocher.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        JeuView test = new JeuView();
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() {
                public void run() {
                    test.pack();
                    test.setVisible(true);
                }
            }
        );
    }
}
