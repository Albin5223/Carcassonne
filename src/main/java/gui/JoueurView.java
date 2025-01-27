package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.main.java.model.general.Joueur;

public class JoueurView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	Joueur joueur;
	JLabel nom,score;
	
	public JoueurView(Joueur j) {
		joueur = j;
		this.setLayout(new GridLayout(2,3));
		this.setOpaque(false);
		
		nom = new JLabel("Nom : " +joueur.getNom());
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setForeground(Color.YELLOW);
		this.add(nom,BorderLayout.CENTER);
		
		score = new JLabel("Score : "+ String.valueOf(joueur.getScore()));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setForeground(Color.YELLOW);
		this.add(score,BorderLayout.CENTER);
	}
	
	public void replace(Joueur j) {
		joueur = j;
		score.setText("Score : "+ String.valueOf(joueur.getScore()));
		nom .setText("Nom : " +joueur.getNom());
		this.repaint();
	}
	
	public void refresh() {
		score.setText("Score : "+ String.valueOf(joueur.getScore()));
		this.repaint();
	}
	
	public void annonceVainqueur() {
		nom.setText(joueur.getNom()+" A GAGNE");
		score.setText("Cliquez pour quitter");
		
		
		this.repaint();
	}
	
	
}
