package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import src.main.java.model.CC.TuileCC;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Ordinateur;
import src.main.java.model.general.Pion;
import src.main.java.model.general.Pion.Couleurs;
import src.main.java.model.general.Tuile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlateauCCG extends PlateauG {

    public PlateauCCG(JeuCCGraphique j) {
        super(j);
    }

	public class InformationCC extends Information {

		protected JButton pion = new JButton("Placer pion ?");
		protected JButton suivant = new JButton("Suivant");

		public InformationCC(int x, int y) {
			super(x, y);

			pion.setEnabled(false);
			suivant.setEnabled(false);
			pion.addActionListener((ActionEvent e) ->{
				placerPion();
				((TuileCCG) currentTuile).addPion(jeu.getCurrentJoueur().popPion());
			});
			suivant.addActionListener((ActionEvent e) ->{
				suivant();
				pion.setEnabled(false);
				suivant.setEnabled(false);
				setDefaultKeys();
			});
			panneauButton.add(pion,BorderLayout.NORTH);
			panneauButton.add(suivant,BorderLayout.NORTH);
		}

		public void removeAllActionListeners(JButton b){
			for (ActionListener a : b.getActionListeners()) {
				b.removeActionListener(a);
			}
		}

		public void setDefaultKeys(){
			removeAllActionListeners(haut);
			removeAllActionListeners(gauche);
			removeAllActionListeners(bas);
			removeAllActionListeners(droit);

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

			droit.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getX() + currentTuile.getWidth() >= PlateauCCG.this.getWidth() - this.getWidth()){
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
		}

		/*
		 * TODO :
		 * Remplacer les touches de deplacements pour deplacer le pion et savoir sur quel paysage exactement le mettre
		 */
		public void setPionKeys(){
			removeAllActionListeners(haut);
			removeAllActionListeners(gauche);
			removeAllActionListeners(bas);
			removeAllActionListeners(droit);

			haut.addActionListener((ActionEvent e) ->{
			});

			gauche.addActionListener((ActionEvent e) ->{
			});

			bas.addActionListener((ActionEvent e) ->{
			});

			droit.addActionListener((ActionEvent e) ->{
			});
		}
		
		public void placerPion(){
			setPionKeys();
		}

		public void tuilePlacee(){
			if(jeu.getCurrentJoueur().isBot()){
				suivant();
			}
			else{
				piocher.setEnabled(false);
				placer.setEnabled(false);
				defausser.setEnabled(false);
				tourner.setEnabled(false);
				abandonner.setEnabled(false);
	
				pion.setEnabled(true);
				suivant.setEnabled(true);
			}
		}

		@Override
		public void placer(){
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
					tuilePlacee();
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
	}

	@Override
	public void initJeu(){
		conteneur = new JPanel();
		conteneur.setLayout(null);
		
        info = new InformationCC(800,0);
        conteneur.add(info);
        
        Tuile t = ((JeuCCGraphique) jeu).setPlateau();
        placerTuile(t,0,0);
        
        this.add(conteneur);
	}

	@Override
	public void placerTuile(Tuile t,int x,int y) {
		TuileCCG t1 = new TuileCCG((TuileCC)t,x,y);
		if (info.getCurrentTuile()!=null){
			t1.setImage(((TuileCCG) info.getCurrentTuile()).getImage());
		}
		
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		tuilesPlateau.add(t1);
	}
	
	@Override
	public TuileCCG positionner(Tuile t,int x,int y) {
		TuileCCG t1 = new TuileCCG((TuileCC)t,x,y);
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		
		return t1;
	}
    

    public class TuileCCG extends TuileG {

        BufferedImage imageR;
		BufferedImage pionG;

		public TuileCCG (TuileCC tuile,int x,int y) {
			this.setBounds(x*100+400, y*100+400, 100, 100);
			this.setLayout(new BorderLayout(5,5));
			try {
				imageR = ImageIO.read(new File("src\\main\\java\\gui\\ImagesCC\\"+tuile.getName()));
				pionG = null;
				
			} catch (IOException e) {
				this.setBackground(Color.YELLOW);
				System.out.println(e);
			}
			this.tuile = tuile;
			this.x = x;
			this.y = y;
		}

		/*
		 * TODO :
		 * L'image du pion ne s'affiche pas sur la tuile, il faut regler ça
		 */
		public void addPion(Pion p){
			try {
				switch (p.getCouleurs()) {
					default:
						pionG = ImageIO.read(new File("src\\main\\java\\gui\\ImagesCC\\bleu.png"));
						break;
					case ROUGE:
						pionG = ImageIO.read(new File("src\\main\\java\\gui\\ImagesCC\\rouge.png"));
						break;
					case VERT:
						pionG = ImageIO.read(new File("src\\main\\java\\gui\\ImagesCC\\vert.png"));
						break;
					case JAUNE:
						pionG = ImageIO.read(new File("src\\main\\java\\gui\\ImagesCC\\jaune.png"));
						break;
				}
			} catch (IOException e) {
				System.out.println("L'image du pion n'a pas été trouvée");
			}
		}

        @Override
        public void init() {}

        protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imageR, 0, 0, null);
			g.drawImage(pionG, 0, 0, null);
		}

        @Override
		public void tourner() {
			this.removeAll();
			tuile.rotation();
			imageR = rotate(imageR);
			conteneur.repaint();	
		}		
		
		public BufferedImage rotate(BufferedImage image) {
	        BufferedImage buffered = (BufferedImage) image;
	        BufferedImage buffered2 = new BufferedImage(buffered.getHeight(),buffered.getWidth(),BufferedImage.TYPE_INT_RGB);
	        for(int x = 0;x < buffered.getWidth();x++) {
	            for(int y = 0;y < buffered.getHeight();y++) {
					buffered2.setRGB(buffered.getHeight()-y-1, x,buffered.getRGB(x, y));
	            }
	        }
	        return buffered2;
	    }
		
		public BufferedImage getImage() {
			return imageR;
		}
		
		public void setImage(BufferedImage i) {
			imageR = i;
		}
        
    }
}
