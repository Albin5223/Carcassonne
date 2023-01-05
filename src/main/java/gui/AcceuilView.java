package src.main.java.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Comparator;

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

	ArrayList<Joueur> listJoueurs;
	
	JPanel container;
	
	JPanel buttonView;
	JPanel panneauJoueur;

	public AcceuilView() {
		jeuDC = new JeuDCGraphique();
		jeuCC = new JeuCCGraphique();
		this.setSize(new Dimension(1000,800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(("Acceuil du Jeu"));
		
		setResizable(false);
		setLocationRelativeTo(null);
		
		listJoueurs = new ArrayList<Joueur>();
		
		container = new JPanel();
		container.setLayout(new GridLayout(3,1));
		
		buttonView = new JPanel();
		buttonView.setLayout(new GridLayout(4,1));
		
		JPanel docu = new JPanel();
		docu.setLayout(new FlowLayout(FlowLayout.CENTER,50,20));
		container.add(docu);
		
		JLabel titre = new JLabel(" Jeu de Carcassonne / Jeu des Dominos-Carrees ");
		titre.setFont(new Font("Arial",Font.ITALIC,40));
		docu.add(titre);
		
		JLabel consigne = new JLabel("( Ajoutez au moins un joueur humain pour jouer )");
		consigne.setFont(new Font("Arial",Font.ITALIC,20));
		docu.add(consigne);
		
		
		
		
		JPanel containerDroite = new JPanel();
		containerDroite.setLayout(new FlowLayout(FlowLayout.CENTER,50,20));
		
		
		panneauJoueur = new JPanel();
		panneauJoueur.setLayout(new FlowLayout(FlowLayout.CENTER,25,5));
		
		
		
		
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
		
		jouer_dominoCarree = new JButton ("Jouer au jeu des Dominos-Carres");
		jouer_dominoCarree.setEnabled(false);
		jouer_dominoCarree.addActionListener((ActionEvent e) ->{

			sortByID();

			for (Joueur joueur : listJoueurs) {
				jeuDC.addJoueur(joueur);
			}


			add.setVisible(false);
			jouer_dominoCarree.setVisible(false);
			jouer_carcassonne.setVisible(false);
			jeuDC.setStrategieForBot();
			javax.swing.SwingUtilities.invokeLater(
					new Runnable() {
						public void run() {
							new PlateauDCG(jeuDC);
							CreationJoueurView.reset();
							AcceuilView.this.dispose();
							
						}
					}
				);
		});
		buttonView.add(jouer_dominoCarree);
		
		jouer_carcassonne = new JButton("Jouer au jeu de Carcassonne");
		jouer_carcassonne.setEnabled(false);
		jouer_carcassonne.addActionListener((ActionEvent e) ->{

			sortByID();

			for (Joueur joueur : listJoueurs) {
				jeuCC.addJoueur(joueur);
			}

			add.setVisible(false);
			jouer_carcassonne.setVisible(false);
			jouer_dominoCarree.setVisible(false);
			jeuCC.setStrategieForBot();
			javax.swing.SwingUtilities.invokeLater(
					new Runnable() {
						public void run() {
							new PlateauCCG(jeuCC);
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
		
		container.add(containerDroite);
		container.add(panneauJoueur);
		this.add(container);

		this.setVisible(true);
	}
	
	public void afficher(JPanel p) {
		container.setVisible(false);
		this.add(p);
	}

	private void sortByID(){
		listJoueurs.sort(new Comparator<Joueur>() {
			@Override
			public int compare(Joueur o1, Joueur o2) {
				if(o1.getID() < o2.getID()){return -1;}
				else if(o1.getID() == o2.getID()){return 0;}
				return 1;
			}
		});
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
					if (idJoueur==0) {
						jouer_dominoCarree.setEnabled(true);
						jouer_carcassonne.setEnabled(true);
					}
					
				}
				else {
					joueur = new Ordinateur(idJoueur,1,jeuDC);
				}
				listJoueurs.add(joueur);
				valider.setEnabled(false);
				
	
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
