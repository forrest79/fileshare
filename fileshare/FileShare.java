package fileshare;

import fileshare.gui.FormMain;
import fileshare.settings.Settings;

/**
 *
 * @author Jakub Trmota
 */
public class FileShare {

	public static final boolean DEBUG = true;

	public static final String NL = System.getProperty("line.separator");

	public static final String NAME = "FileShare";
	public static final double VERSION = 0.01;
	public static final String EMAIL = "trmotjak@fel.cvut.cz";

	private static String appPath = "";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		if (!Settings.getSettings().loadFromFile()) {
			if (DEBUG) {
				System.err.println("Nastavení ze souboru se nepodařilo načíst...");
			}
		}

		// TODO Nacti ostatni pocitace

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
						new FormMain().createAndShow();
				}
		});
	}

	public static String getAppDir() {
		if (appPath.equals("")) {
			String os = System.getProperty("os.name");
			String slash = (os.toLowerCase().indexOf("windows") > -1) ? "\\" : "/";
			String path = System.getProperty("user.dir");

			appPath = (path.substring(path.length() - 2, path.length() - 1).equals(slash)) ? path : path + slash;
		}

		return appPath;
	}
}
