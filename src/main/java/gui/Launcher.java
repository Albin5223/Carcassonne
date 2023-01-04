	package src.main.java.gui;

public class Launcher {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					AcceuilView a = new AcceuilView();
				}
			}
		);
		
	}
}
 