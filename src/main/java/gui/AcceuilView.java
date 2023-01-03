package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
	JeuDCGraphique jeuDC;
	JeuCCGraphique jeuCC;
	JButton add;
	JButton jouer_dominoCarree;
	JButton jouer_carcassonne;
	JButton quitter;
	
	
	JPanel container;
	
	JPanel buttonView;
	JPanel panneauJoueur;

	public AcceuilView() {
		jeuDC = new JeuDCGraphique();
		jeuCC = new JeuCCGraphique();
		this.setSize(new Dimension(1000,800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(("Domino Carree"));
		
		
		container = new JPanel();
		container.setLayout(new GridLayout(1,2));
		
		buttonView = new JPanel();
		buttonView.setLayout(new GridLayout(4,1));
		
		
		JPanel containerDroite = new JPanel();
		
		
		JLabel consigne = new JLabel("Ajouter au moins un joueur pour jouer");
		containerDroite.add(consigne);
		
		
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
			jouer_carcassonne.setVisible(false);
			jeuDC.setStrategieForBot(jeuDC);
			javax.swing.SwingUtilities.invokeLater(
					new Runnable() {
						public void run() {
							PlateauDCG p = new PlateauDCG(jeuDC);
							CreationJoueurView.reset();
							AcceuilView.this.dispose();
							
						}
					}
				);
		});
		buttonView.add(jouer_dominoCarree);
		
		jouer_carcassonne = new JButton("Jouer au jeuDC de Carcassonne");
		jouer_carcassonne.setEnabled(false);
		jouer_carcassonne.addActionListener((ActionEvent e) ->{
			add.setVisible(false);
			jouer_carcassonne.setVisible(false);
			jouer_dominoCarree.setVisible(false);
			jeuCC.setStrategieForBot(jeuCC);
			javax.swing.SwingUtilities.invokeLater(
					new Runnable() {
						public void run() {
							PlateauCCG p = new PlateauCCG(jeuCC);
							CreationJoueurView.reset();
							AcceuilView.this.dispose();	
						}
					}
				);
		});
		buttonView.add(jouer_carcassonne);
		
		
		quitter = new JButton("Quitter");
		quitter.addActionListener((ActionEvent e) ->{
			this.dispose();
		});
		buttonView.add(quitter);
		
		containerDroite.add(buttonView);
		container.add(panneauJoueur);
		container.add(containerDroite);
		this.add(container);

		this.setVisible(true);
	}
	
	public void afficher(JPanel p) {
		container.setVisible(false);
		this.add(p);
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
					joueur = new Ordinateur(idJoueur,1,jeuDC);
				}
				
				jeuDC.addJoueur(joueur);
				jeuCC.addJoueur(joueur);
				valider.setEnabled(false);
				jouer_dominoCarree.setEnabled(true);
				jouer_carcassonne.setEnabled(true);
				
			});
			
			this.add(container);
			
			ajouter();
			
			id++;
		}
		
		public void ajouter() {
			panneauJoueur.add(this);
			panneauJoueur.repaint();
		}
		
		public static void reset() {
			id=0;
		}
	}

}
