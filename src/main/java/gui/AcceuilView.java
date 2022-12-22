package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.main.java.model.general.Joueur;
import src.main.java.model.general.Ordinateur;

public class AcceuilView extends JFrame{
	
	int x_pos = 50;
	int nb_Joueur;
	JeuDCGraphique jeu;
	JButton add;
	JButton jouer_dominoCarree;
	
	
	JPanel container;
	
	JPanel buttonView;
	JPanel panneauJoueur;

	public AcceuilView() {
		jeu = new JeuDCGraphique();
		this.setSize(new Dimension(1000,800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle(("Domino Carree"));
		
		
		container = new JPanel();
		container.setLayout(new GridLayout(1,2));
		
		buttonView = new JPanel();
		panneauJoueur = new JPanel();
		
		add = new JButton("Ajouter un joueur");
		
		add.addActionListener((ActionEvent e) ->{
			CreationJoueurView cjv = new CreationJoueurView(x_pos);
			cjv.ajouter();
			buttonView.setVisible(false);
			buttonView.setVisible(true);
			nb_Joueur+=1;
			if (nb_Joueur == 4) {
				add.setEnabled(false);
			}
		});
		
		buttonView.add(add);
		
		jouer_dominoCarree = new JButton ("Jouer aux dominos carres");
		jouer_dominoCarree.setEnabled(false);
		jouer_dominoCarree.addActionListener((ActionEvent e) ->{
			add.setVisible(false);
			jouer_dominoCarree.setVisible(false);
			
			javax.swing.SwingUtilities.invokeLater(
					new Runnable() {
						public void run() {
							PlateauDominoCarre p = new PlateauDominoCarre(jeu);
							
						}
					}
				);
			jeu.lancerPartie();
		});
		buttonView.add(jouer_dominoCarree);
		
		
		container.add(panneauJoueur);
		container.add(buttonView);
		this.add(container);
	}
	
	
	public class CreationJoueurView extends JPanel{
		
		JTextField demanderNom;
		JCheckBox estRobot;
		JButton valider;
		static int id;
		JPanel container;
		int idJoueur;
		
		public CreationJoueurView(int x) {
			idJoueur = id;
			this.setBackground(Color.PINK);
			
			container = new JPanel();
			container.setLayout(new GridLayout(5,1));
			
			
			JLabel nom = new JLabel("Entrez un pseudo : ");
			container.add(nom);
			
			demanderNom = new JTextField();
			container.add(demanderNom);
			
			if (id != 0) {
				estRobot = new JCheckBox("Robot ?");
				container.add(estRobot);
			}
			
			
			valider = new JButton("Valider");
			container.add(valider);
			
			valider.addActionListener((ActionEvent e) ->{
				Joueur joueur;
				if (estRobot == null || !estRobot.isSelected()) {
					joueur = new Joueur(demanderNom.getText(),idJoueur);
				}
				else {
					joueur = new Ordinateur(idJoueur,1,jeu);
				}
				
				jeu.addJoueur(joueur);
				valider.setEnabled(false);
				jouer_dominoCarree.setEnabled(true);
			});
			
			this.add(container);
			
			ajouter();
			
			id++;
		}
		
		public void ajouter() {
			panneauJoueur.add(this);
			panneauJoueur.repaint();
			
		}
	}

}
