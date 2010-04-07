package fileshare.settings;

import com.sun.corba.se.impl.orbutil.GetPropertyAction;
import fileshare.FileShare;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Třída udržující nastavení, načítání z disku a ukládáná na disk a generující XML se seznamem souborů.
 *
 * @author Jakub Trmota
 */
public class Settings {

	public static final String PASSWORD_KEY = "%@#&sdfs2673fsdf";

	private static Settings settingsInstance = null;

	private File file = null;

	private int port = 3278;
	private String password = "";
	private boolean automaticUpload = true;
	private String downloadDir = "";
	private ArrayList<String> shareDirs = null;

	private Settings() {
		file = new File(FileShare.getAppDir() + "settings.ini");

		downloadDir = FileShare.getAppDir();

		shareDirs = new ArrayList<String>();
	}

	public static Settings getSettings() {
		if (settingsInstance == null) {
			settingsInstance = new Settings();
		}

		return settingsInstance;
	}

	public boolean loadFromFile() {
		if (file.exists()) {
			try {
				BufferedReader input = new BufferedReader(new FileReader(file));

				String line = "";

				while ((line = input.readLine()) != null) {
					line = line.trim();

					if (line.startsWith("#")) { // Komentar
						continue;
					}

					String[] params = line.split("=");

					if (params.length >= 2) {
						String key = params[0].trim().toLowerCase();
						String value = params[1].trim();

						if (key.equals("port")) {
							try {
								port = Integer.parseInt(value);
							} catch (Exception e) {
								if (FileShare.DEBUG) {
									System.err.println("Port error value: " + e.getMessage());
								}

								continue;
							}
						} else if(key.equals("password")) {
							password = Settings.decrypt(PASSWORD_KEY, value);
						} else if(key.equals("automaticupload")) {
							automaticUpload = (value.equals("1"));
						} else if(key.equals("downloaddir")) {
							downloadDir = value;
						} else if(key.equals("sharedir")) {
							shareDirs.add(value);
						}
					}
				}

				input.close();
			} catch (IOException e) {
				if (FileShare.DEBUG) {
					System.err.println("Settings load error: " + e.getMessage());
				}

				return false;
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean saveToFile() {
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file));

			output.write("Port=" + String.valueOf(port) + FileShare.NL);
			output.write("Password=" + Settings.encrypt(PASSWORD_KEY, password) + FileShare.NL);
			output.write("AutomaticUpload=" + (automaticUpload ? "1" : "0") + FileShare.NL);
			output.write("DownloadDir=" + downloadDir + FileShare.NL);

			for (int i = 0; i < shareDirs.size(); i++) {
				output.write("ShareDir=" + shareDirs.get(i) + FileShare.NL);
			}
			
			output.close();
		} catch (IOException e) {
			if (FileShare.DEBUG) {
				System.err.println("Settings write error: " + e.getMessage());
			}

			return false;
		}

		return true;
	}

	public boolean generateShareDirsXML() {
		return true;
	}

	public boolean isAutomaticUpload() {
		return automaticUpload;
	}

	public void setAutomaticUpload(boolean automaticUpload) {
		this.automaticUpload = automaticUpload;
	}

	public String getDownloadDir() {
		return downloadDir;
	}

	public void setDownloadDir(String downloadDir) throws Exception {
		if (downloadDir.contains("=")) {
			throw new Exception("Adresář nesmí obsahovat znak '='!");
		}

		this.downloadDir = downloadDir;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String[] getShareDirs() {
		String[] dirs = new String[shareDirs.size()];

		for (int i = 0; i < shareDirs.size(); i++) {
			dirs[i] = shareDirs.get(i);
		}

		return dirs;
	}

	public void addShareDir(String dir) throws Exception {
		if (dir.contains("=")) {
			throw new Exception("Adresář nesmí obsahovat znak '='!");
		}

		shareDirs.add(dir);
	}

	public void clearShareDirs() {
		shareDirs.clear();
	}

	public static String encrypt(String key, String text) {
		int[] s = new int[256];
		for (int i = 0; i < 256; i++) {
			s[i] = i;
		}
		int j = 0;
		int x;
		for (int i = 0; i < 256; i++) {
			j = (j + s[i] + key.charAt(i % key.length())) % 256;
			x = s[i];
			s[i] = s[j];
			s[j] = x;
		}
		int i = 0;
		j = 0;
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < text.length(); y++) {
			i = (i + 1) % 256;
			j = (j + s[i]) % 256;
			x = s[i];
			s[i] = s[j];
			s[j] = x;
			sb.append(new String(new int[] { text.charAt(y) ^ s[(s[i] + s[j]) % 256] }, 0, 1));
		}
		return sb.toString();
	}

	public static String decrypt(String key, String crypt) {
		return encrypt(key, crypt);
	}
}
