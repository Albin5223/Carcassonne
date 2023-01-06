package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.main.java.gui.PlateauCCG.TuileCCG.PionG;
import src.main.java.model.CC.TuileCC;
import src.main.java.model.general.Pion;
import src.main.java.model.general.PionsVideException;
import src.main.java.model.general.Tuile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlateauCCG extends PlateauG {

    public PlateauCCG(JeuCCGraphique j) {
        super(j);
        this.setTitle(("Jeu de Carcassonne"));
    }

	public class InformationCC extends Information {

		protected JButton placerPion = new JButton("Placer pion ?");
		protected JButton suivant = new JButton("Suivant");

		public InformationCC(int x, int y) {
			super(x, y);

			placerPion.setEnabled(false);
			suivant.setEnabled(false);
			placerPion.addActionListener((ActionEvent e) ->{
				placerPion();
			});
			suivant.addActionListener((ActionEvent e) ->{
				suivant();
				placerPion.setEnabled(false);
				suivant.setEnabled(false);
				setDefaultKeys();
			});
			panneauButton.add(placerPion,BorderLayout.NORTH);
			panneauButton.add(suivant,BorderLayout.NORTH);
		}

		@Override
		protected void glissePlateauHaut(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()-100);
				PionG p = ((TuileCCG) t).pionG;
				if(p != null){
					p.setLocation(p.getX(), p.getY()-100);
					p.refresh();
				}
			}
			dy += 1;
		}

		@Override
		protected void glissePlateauBas(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()+100);
				PionG p = ((TuileCCG) t).pionG;
				if(p != null){
					p.setLocation(p.getX(), p.getY()+100);
					p.refresh();
				}
			}
			dy -= 1;
		}

		@Override
		protected void glissePlateauDroite(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX()+100, t.getY());
				PionG p = ((TuileCCG) t).pionG;
				if(p != null){
					p.setLocation(p.getX()+100, p.getY());
					p.refresh();
				}
			}
			dx -= 1;
		}

		@Override
		protected void glissePlateauGauche(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX()-100, t.getY());
				PionG p = ((TuileCCG) t).pionG;
				if(p != null){
					p.setLocation(p.getX()-100, p.getY());
					p.refresh();
				}
			}
			dx += 1;
		}

		public void removeAllActionListeners(JButton b){
			for (ActionListener a : b.getActionListeners()) {
				b.removeActionListener(a);
			}
		}

		public void removeAllActionListenersFleches(){
			removeAllActionListeners(haut);
			removeAllActionListeners(gauche);
			removeAllActionListeners(bas);
			removeAllActionListeners(droit);
		}

		public void setDefaultKeys(){
			removeAllActionListenersFleches();

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

		public void setPionKeys(){
			removeAllActionListenersFleches();

			PionG p = ((TuileCCG) tuilesPlateau.get(tuilesPlateau.size()-1)).pionG;

			haut.addActionListener((ActionEvent e) ->{
				if(p.y > 0){
					p.y--;
					p.refresh();
				}
			});

			gauche.addActionListener((ActionEvent e) ->{
				if(p.x > 0){
					p.x--;
					p.refresh();
				}
			});

			bas.addActionListener((ActionEvent e) ->{
				if(p.y < 3){
					p.y++;
					p.refresh();
				}
			});

			droit.addActionListener((ActionEvent e) ->{
				if(p.x < 3){
					p.x++;
					p.refresh();
				}
			});
		}
		
		public void placerPion(){
			try {
				((TuileCCG) tuilesPlateau.get(tuilesPlateau.size()-1)).placerPion();
				setPionKeys();
				placerPion.setEnabled(false);
			} catch (PionsVideException e) {
				System.out.println("bug au niveau des pions");
			}
		}

		// On regarde quoi faire après que la tuile soit posée
		public void tuilePlacee(){
			if(jeu.getCurrentJoueur().isBot()){		// Si c'est un bot, on passe directement au joueur suivant
				suivant();
			}
			else{								// Si c'est un humain

				piocher.setEnabled(false);
				placer.setEnabled(false);
				defausser.setEnabled(false);
				tourner.setEnabled(false);
				abandonner.setEnabled(false);
	
				removeAllActionListenersFleches();

				if(!jeu.getCurrentJoueur().pionsIsEmpty()){		// Si il lui reste des pions, on lui laisse le choix
					placerPion.setEnabled(true);
					suivant.setEnabled(true);
				}
				else{				// Sinon, on passe directement au joueur suivant
					suivant();
				}
			}
		}

		@Override
		public void placer(){
			int x = (currentTuile.getX()-400)/100;
			int y = (currentTuile.getY()-400)/100;
			if (tuile != null) {
				if (jeu.placer(tuile,x+dx, y+dy)){
					conteneur.remove(currentTuile);
					placerTuile(tuile,x,y);
					message.setText("Bien joue");
					message.setForeground(Color.WHITE);
					panneauButton.add(message);
					
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

        protected BufferedImage imageR;
		protected Pion pion;
		protected PionG pionG = new PionG();

		protected class PionG extends JLabel {

			protected int x = 0;
			protected int y = 0;

			public PionG() {
				super(new ImageIcon(stringPion()));
				this.setSize(25, 25);
				this.setVisible(false);
				conteneur.add(this);
			}

			public void refresh(){
				this.setLocation(TuileCCG.this.getX() + x*getWidth(), TuileCCG.this.getY() + y*getHeight());
				conteneur.repaint();
				this.repaint();
			}

			public void placerPion() throws PionsVideException {
				pion = jeu.getCurrentJoueur().popPion();
				refresh();
				this.setVisible(true);
			}
		}

		public void placerPion() throws PionsVideException {
			pionG.placerPion();
		}

		public String stringPion(){
			switch (jeu.getCurrentJoueur().getCouleursPion()) {
				default:
					return "src\\main\\java\\gui\\ImagesCC\\bleu.png";
				case ROUGE:
					return "src\\main\\java\\gui\\ImagesCC\\rouge.png";
				case VERT:
					return "src\\main\\java\\gui\\ImagesCC\\vert.png";
				case JAUNE:
					return "src\\main\\java\\gui\\ImagesCC\\jaune.png";
			}
		}

		public TuileCCG (TuileCC tuile,int x,int y) {
			this.setBounds(x*100+400, y*100+400, 100, 100);
			try {
				imageR = ImageIO.read(new File("src\\main\\java\\gui\\ImagesCC\\"+tuile.getName()));
				
			} catch (IOException e) {
				this.setBackground(Color.YELLOW);
				System.out.println(e);
			}
			this.tuile = tuile;
			this.x = x;
			this.y = y;
		}

        @Override
        public void init() {}

        protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imageR, 0, 0, null);
		}

        @Override
		public void tourner() {
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