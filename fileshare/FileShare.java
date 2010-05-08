package fileshare;

import fileshare.gui.FormMain;
import fileshare.settings.Settings;
import fileshare.settings.Users;

/**
 * Main class.
 *
 * @author Jakub Trmota (Forrest79)
 */
public class FileShare {

	public static final boolean DEBUG = false;

	public static final String NL = System.getProperty("line.separator");
	public static String SLASH = "/";

	public static final String NAME = "FileShare";
	public static final double VERSION = 1.00;
	public static final String EMAIL = "info@forrest79.net";

	private static String appPath = "";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SLASH = (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) ? "\\" : "/";

		if (!Settings.getSettings().loadFromFile()) {
			if (DEBUG) {
				System.err.println("Load settings error...");
			}
		}

		if (!Users.getUsers().loadFromFile()) {
			if (DEBUG) {
				System.err.println("Load users error...");
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
