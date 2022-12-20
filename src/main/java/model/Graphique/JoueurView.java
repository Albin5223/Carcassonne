package src.main.java.model.Graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.main.java.model.general.Joueur;

public class JoueurView extends JPanel{
	
	Joueur joueur;
	
	public JoueurView(Joueur j) {
		joueur = j;
		this.setLayout(new GridLayout(2,3));
		this.setBackground(Color.BLUE);
		
		JLabel nom = new JLabel("Nom : " +joueur.getNom());
		nom.setForeground(Color.YELLOW);
		this.add(nom,BorderLayout.CENTER);
		
		JLabel score = new JLabel("Score : "+ String.valueOf(joueur.getScore()));
		score.setForeground(Color.YELLOW);
		this.add(score,BorderLayout.CENTER);
	}

	
}
