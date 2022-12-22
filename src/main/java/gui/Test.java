package src.main.java.gui;

public class Test {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					//PlateauDominoCarre p = new PlateauDominoCarre();
					AcceuilView a = new AcceuilView();
				}
			}
		);
		
	}
}
 