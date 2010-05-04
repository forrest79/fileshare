package fileshare;

import fileshare.gui.FormMain;
import fileshare.settings.Settings;
import fileshare.settings.Users;

/**
 * Hlavní třída.
 *
 * @author Jakub Trmota
 */
public class FileShare {

	public static final boolean DEBUG = true;

	public static final String NL = System.getProperty("line.separator");
	public static String SLASH = "/";

	public static final String NAME = "FileShare";
	public static final double VERSION = 0.8;
	public static final String EMAIL = "trmotjak@fel.cvut.cz";

	private static String appPath = "";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SLASH = (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) ? "\\" : "/";

		if (!Settings.getSettings().loadFromFile()) {
			if (DEBUG) {
				System.err.println("Nastavení ze souboru se nepodařilo načíst...");
			}
		}

		if (!Users.getUsers().loadFromFile()) {
			if (DEBUG) {
				System.err.println("Uživatele ze souboru se nepodařilo načíst...");
			}
		}

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
						new FormMain().createAndShow();
				}
		});
	}

	public static String getAppDir() {
		if (appPath.equals("")) {
			String path = System.getProperty("user.dir");

			appPath = (path.substring(path.length() - 2, path.length() - 1).equals(SLASH)) ? path : path + SLASH;
		}

		return appPath;
	}
}
