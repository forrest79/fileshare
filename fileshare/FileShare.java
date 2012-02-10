package fileshare;

import fileshare.gui.FormMain;
import fileshare.settings.Settings;
import fileshare.settings.Users;

/**
 * Main class.
 *
 * @author Jakub Trmota | Forrest79
 */
public class FileShare {
	/**
	 * Is debug?
	 */
	public static final boolean DEBUG = false;

	/**
	 * New line separator from system.
	 */
	public static final String NL = System.getProperty("line.separator");

	/**
	 * Slash.
	 */
	public static String SLASH = "/";

	/**
	 * Application name.
	 */
	public static final String NAME = "FileShare";

	/**
	 * Application version.
	 */
	public static final double VERSION = 1.1;

	/**
	 * Application developer.
	 */
	public static final String DEVELOPER = "Jakub Trmota";

	/**
	 * Application developer email.
	 */
	public static final String EMAIL = "info@forrest79.net";

	/**
	 * Application path.
	 */
	private static String appPath = "";

	/**
	 * Application main method.
	 *
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
			@Override
			public void run() {
				new FormMain().createAndShow();
			}
		});
	}

	/**
	 * Get application dir.
	 *
	 * @return
	 */
	public static String getAppDir() {
		if (appPath.equals("")) {
			String path = System.getProperty("user.dir");

			appPath = (path.substring(path.length() - 2, path.length() - 1).equals(SLASH)) ? path : path + SLASH;
		}

		return appPath;
	}
}
