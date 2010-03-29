package fileshare;

import fileshare.gui.FormMain;

/**
 *
 * @author Jakub Trmota
 */
public class FileShare {

	public static final boolean DEBUG = true;

	public static final String NAME = "FileShare";
	public static final double VERSION = 0.01;
	public static final String EMAIL = "trmotjak@fel.cvut.cz";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO Nacti nastaveni or vytvor prazdne

		// TODO Nacti ostatni pocitace

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
						new FormMain().createAndShow();
				}
		});
	}
}
