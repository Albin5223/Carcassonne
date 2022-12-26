package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.main.java.model.general.Jeu;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Ordinateur;
import src.main.java.model.general.Tuile;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class PlateauG extends JFrame {

    int dx;
	int dy;
	ArrayList<TuileG> tuilesPlateau = new ArrayList<TuileG>();
	JPanel conteneur;
	JPanel information;
	Jeu jeu;
	Information info;
	
	public PlateauG(Jeu j) {
		this.setSize(new Dimension(1000,800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jeu = j;
		initJeu();

		this.setVisible(true);
	}
	
	public abstract void initJeu();
	
	public abstract void placerTuile(Tuile t,int x,int y);
	
	public abstract TuileG positionner(Tuile t,int x,int y);

    public class Information extends JPanel implements KeyListener{
		
		TuileG currentTuile;
		Tuile tuile;
		JButton piocher;
		JButton placer;
		JButton defausser;
		JButton tourner;
		JButton abandonner;
		JoueurView jv1;
		
		JTextField xCord;
		JTextField yCord;
		
		JPanel panneauButton;
		JPanel infoCoord;
		JLabel message;
		
		JButton gauche;
		JButton droit;
		JButton haut;
		JButton bas;

		// TODO : ne marche pas encore
		@Override
		public void keyTyped(KeyEvent e){
		   System.out.println("touche");
		   switch (e.getKeyCode()) {
			   case KeyEvent.VK_KP_UP:
				   haut.doClick();
				   break;
			   case KeyEvent.VK_KP_DOWN:
				   bas.doClick();
				   break;
			   case KeyEvent.VK_KP_RIGHT:
				   droit.doClick();
				   break;
			   case KeyEvent.VK_KP_LEFT:
				   gauche.doClick();
				   break;
			   default:
				   break;
		   }
	   }

	   @Override
	   public void keyPressed(KeyEvent e) {}

	   @Override
	   public void keyReleased(KeyEvent e) {}
		
		public Information(int x,int y) {
			this.setBounds(x, y, 200, PlateauG.this.getHeight());
			this.setBackground(Color.BLUE);
			this.setLayout(new GridLayout(4,1,100,25));

			addKeyListener(this);
			addWindowListener(new WindowAdapter() {
				public void windowOpened(WindowEvent e) { 
				requestFocus();	
				}
			});
			
			jv1 = new JoueurView(jeu.getCurrentJoueur());
			panneauButton = new JPanel();
			panneauButton.setBackground(Color.BLUE);
		
			
			
			piocher = new JButton("Piocher");
			panneauButton.add(piocher,BorderLayout.NORTH);
			
			piocher.addActionListener((ActionEvent e) ->{
				piocher();
			});
			
			placer = new JButton("Placer");
			placer.setEnabled(false);
			placer.addActionListener((ActionEvent e) ->{
				placer();
			});
			panneauButton.add(placer,BorderLayout.NORTH);
			
			defausser = new JButton("Defausser");
			defausser.setEnabled(false);
			defausser.addActionListener((ActionEvent e) ->{
				defausser();
				suivant();
			});
			panneauButton.add(defausser);
			
			tourner = new JButton("Rotation");
			tourner.setEnabled(false);
			tourner.addActionListener((ActionEvent e) ->{
				currentTuile.tourner();
			});
			panneauButton.add(tourner);
			
			
			
			
			abandonner = new JButton("Abandonner");
			panneauButton.add(abandonner);
			abandonner.addActionListener((ActionEvent e) ->{
				jeu.abandonner();
				if(currentTuile != null) {
					defausser();
				}

				suivant();
				
			});
			
			this.add(jv1);
			this.add(panneauButton);
			
			infoCoord = new JPanel();
			infoCoord.setBackground(Color.BLUE);
			infoCoord.setLayout(new GridLayout(2,6));
			
			JPanel vide1 = new JPanel();
			vide1.setBackground(this.getBackground());
			infoCoord.add(vide1); 
			
			haut = new JButton("^");
			infoCoord.add(haut);
			haut.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getY()<=0){
						glissePlateauBas();
					}
					else{
						currentTuile.setLocation(currentTuile.getX(),currentTuile.getY()-100);
					}
				}
				else{
					glissePlateauBas();
				}
			});

			JPanel vide2 = new JPanel();
			vide2.setBackground(this.getBackground());
			infoCoord.add(vide2);

			gauche = new JButton("<");
			infoCoord.add(gauche);
			gauche.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getX()<=0){
						glissePlateauDroite();
					}
					else{
						currentTuile.setLocation(currentTuile.getX()-100,currentTuile.getY());
					}
				}
				else{
					glissePlateauDroite();
				}
			});
			
			bas = new JButton("v");
			infoCoord.add(bas);
			bas.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getY()+currentTuile.getHeight() >= this.getHeight()){
						glissePlateauHaut();
					}
					else{
						currentTuile.setLocation(currentTuile.getX(),currentTuile.getY()+100);
					}
				}
				else{
					glissePlateauHaut();
				}
			});
			
			droit = new JButton(">");
			infoCoord.add(droit);
			droit.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getX() + currentTuile.getWidth() >= PlateauG.this.getWidth() - this.getWidth()){
						glissePlateauGauche();
					}
					else{
						currentTuile.setLocation(currentTuile.getX()+100,currentTuile.getY());
					}
				}
				else{
					glissePlateauGauche();
				}
			});
			
			this.add(infoCoord);
			
			message = new JLabel();
		}

		private void glissePlateauHaut(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()-100);
			}
			dy += 1;
		}

		private void glissePlateauBas(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()+100);
			}
			dy -= 1;
		}

		private void glissePlateauDroite(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX()+100, t.getY());
			}
			dx -= 1;
		}

		private void glissePlateauGauche(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX()-100, t.getY());
			}
			dx += 1;
		}

		public TuileG getCurrentTuile() {
			return currentTuile;
		}
		
		public void defausser() {
			conteneur.remove(currentTuile);
			conteneur.repaint();
			tourner.setEnabled(false);
			placer.setEnabled(false);
			defausser.setEnabled(false);
			currentTuile = null;
		}
		
		public void quitter() {
			this.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {PlateauG.this.dispose();}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
				
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
				abandonner.setEnabled(false);
				quitter();
				return;
			}
			jv1.replace(jeu.getCurrentJoueur());
			piocher.setEnabled(true);
			placer.setEnabled(false);
			abandonner.setEnabled(true);
			if (message!=null) {
				panneauButton.remove(message);
			}
			if (jeu.getCurrentJoueur().isBot()) {
				Tuile tuile = jeu.piocher(jeu.getCurrentJoueur());
				Joueur j = jeu.getCurrentJoueur();
				if (((Ordinateur) j).jouerTour(tuile)) {
					PlateauG.this.placerTuile(tuile,tuile.getCoordonnee().getX()-dx,tuile.getCoordonnee().getY()-dy);
				}
				suivant();
			}
		}
			
		public void placer() {
			int x = (currentTuile.getX()-400)/100;
			int y = (currentTuile.getY()-400)/100;
			if (tuile != null) {
				if (jeu.placer(tuile,x+dx, y+dy)){
					conteneur.remove(currentTuile);
					conteneur.repaint();
					placerTuile(tuile,x,y);
					conteneur.repaint();
					message.setText("Bien joue");
					message.setForeground(Color.WHITE);
					panneauButton.add(message);
					this.setVisible(false);
					this.setVisible(true);
					
					defausser.setEnabled(false);
					tourner.setEnabled(false);
					placer.setEnabled(false);
					abandonner.setEnabled(false);
					suivant();
					jv1.refresh();
					
					currentTuile = null;
					}
				else {
					message.setText("Erreur dans le placement");
					message.setForeground(Color.RED);
					panneauButton.add(message);
					this.setVisible(false);
					this.setVisible(true);
		
				}
			}		
		}
		
		public void piocher() {
			tuile = jeu.piocher(jeu.getCurrentJoueur());
			currentTuile = positionner(tuile,-3,-3);
			
			tourner.setEnabled(true);
			defausser.setEnabled(true);
			placer.setEnabled(true);
			piocher.setEnabled(false);
		}
	}
	
	public abstract class TuileG extends JPanel {
		
		Tuile tuile;
		int x;
		int y;
		
		public abstract void init();
		
		public abstract void tourner();
	}
    
}
