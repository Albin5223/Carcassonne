package src.main.java.launcher;

import src.main.java.gui.AcceuilView;
import src.main.java.model.DC.JeuDCShell;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		demanderChoix();
	}

	@SuppressWarnings("resource")
	private static void demanderChoix(){
		boolean boucle = true;
		while (boucle) {
			System.out.println("Que voulez-vous lancer ?");
			System.out.println("| G -> Version graphique : Dominos-Carrées + Carcassonne");
			System.out.println("| S -> Version shell des Dominos-Carrées");
			Scanner sc = new Scanner(System.in);
			try {
				String choix = sc.next();
				choix = choix.toUpperCase();
				if(choix.length()>1 || (choix.charAt(0) != 'G' && choix.charAt(0) != 'S')){
					System.err.println("- Rentrez un choix valide (R / P). -");
                    boucle = true;
				}
				else{
					switch (choix.charAt(0)) {
						case 'G':
							lancerGraphique();
							boucle = false;
							break;
						case 'S':
							lancerShell();
							boucle = false;
							break;
					}
				}
			} catch (InputMismatchException e) {
				System.err.println("- Rentrez un choix valide (G / S). -");
                boucle = true;
			}
		}
	}

	private static void lancerGraphique(){
		javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					new AcceuilView();
				}
			}
		);
	}

	private static void lancerShell(){
		JeuDCShell jeu = new JeuDCShell();
		jeu.lancerPartie();
	}
}
 