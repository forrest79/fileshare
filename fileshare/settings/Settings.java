package fileshare.settings;

import fileshare.FileShare;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Třída udržující nastavení, načítání z disku a ukládáná na disk a generující XML se seznamem souborů.
 *
 * @author Jakub Trmota
 */
public class Settings {

	public static final String PASSWORD_KEY = "%@#&sdfs2673fsdf";
	public static final int DEFAULT_PORT = 3278;

	private static Settings settingsInstance = null;

	private File fileSettings = null;
	private File fileShare = null;

	private int port = DEFAULT_PORT;
	private String password = "";
	private String downloadDir = "";
	private int horizontalSplit = 250;
	private int verticalSplit = 600;
	private ArrayList<String> shareDirs = null;

	private Settings() {
		fileSettings = new File(FileShare.getAppDir() + "settings.ini");
		fileShare = new File(FileShare.getAppDir() + "share.xml");

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
		if (fileSettings.exists()) {
			BufferedReader input = null;
			try {
				input = new BufferedReader(new FileReader(fileSettings));

				String line = "";

				while ((line = input.readLine()) != null) {
					line = line.trim();

					if (line.startsWith("#")) { // Komentar
						continue;
					}

					String[] params = line.split("=", 2);

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
						} else if (key.equals("verticalsplit")) {
							try {
								verticalSplit = Integer.parseInt(value);
							} catch (Exception e) {
								if (FileShare.DEBUG) {
									System.err.println("Port error value: " + e.getMessage());
								}

								continue;
							}
						} else if (key.equals("horizontalsplit")) {
							try {
								horizontalSplit = Integer.parseInt(value);
							} catch (Exception e) {
								if (FileShare.DEBUG) {
									System.err.println("Port error value: " + e.getMessage());
								}

								continue;
							}
						} else if(key.equals("password")) {
							password = Settings.decrypt(value);
						} else if(key.equals("downloaddir")) {
							downloadDir = value;
						} else if(key.equals("sharedir")) {
							shareDirs.add(value);
						}
					}
				}
			} catch (IOException e) {
				if (FileShare.DEBUG) {
					System.err.println("Settings load error: " + e.getMessage());
				}
			} finally {
				try {
					input.close();
				} catch (IOException ex) {
					System.err.println("Settings load error: " + ex.getMessage());
				}
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean saveToFile() {
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(fileSettings));

			output.write("Port=" + String.valueOf(port) + FileShare.NL);
			output.write("Password=" + Settings.encrypt(password) + FileShare.NL);
			output.write("DownloadDir=" + downloadDir + FileShare.NL);
			output.write("VerticalSplit=" + verticalSplit + FileShare.NL);
			output.write("HorizontalSplit=" + horizontalSplit + FileShare.NL);

			for (int i = 0; i < shareDirs.size(); i++) {
				output.write("ShareDir=" + shareDirs.get(i) + FileShare.NL);
			}
		} catch (IOException e) {
			if (FileShare.DEBUG) {
				System.err.println("Settings write error: " + e.getMessage());
			}
		} finally {
			try {
				output.close();
			} catch (IOException ex) {
				System.err.println("Settings write error: " + ex.getMessage());
			}
		}

		return true;
	}

	public boolean generateShareDirsXml() {
		String xml = "<share>";

		for (int i = 0; i < shareDirs.size(); i++) {
			xml += listDir(new File(shareDirs.get(i)));
		}

		xml += "</share>";

		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(fileShare));

			output.write(xml);

			output.close();
		} catch (IOException e) {
			if (FileShare.DEBUG) {
				System.err.println("Share XML write error: " + e.getMessage());
			}

			return false;
		}

		return true;
	}

	public String getShareDirsXml() {
		String xml = "";

		if (fileShare.exists()) {
			BufferedReader input = null;
			try {
				input = new BufferedReader(new FileReader(fileShare));

				String line = "";

				while ((line = input.readLine()) != null) {
					xml += line;
				}
			} catch (IOException e) {
				if (FileShare.DEBUG) {
					System.err.println("Share dirs load error: " + e.getMessage());
				}
			} finally {
				try {
					input.close();
				} catch (IOException ex) {
					System.err.println("Share dirs load error: " + ex.getMessage());
				}
			}
		}
		
		return xml;
	}

	private String listDir(File dir) {
		if (!dir.isDirectory() || !dir.exists()) {
			return "";
		}

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return !name.startsWith(".");
			}
		};

		String xml = "<dir name=\"" + dir.getName().replace("&", "&amp;") + "\">";

		File[] files = dir.listFiles(filter);
		if (files != null) {
			for (int x = 0; x < files.length; x++) {
				File one = files[x];

				if (one.isDirectory()) {
					xml += listDir(one);
				} else {
					xml += "<file path=\"" + one.getPath().replace("&", "&amp;") + "\" name=\"" + one.getName().replace("&", "&amp;") + "\" size=\"" + one.length() + "\" />";
				}
			}
		}

		xml += "</dir>";

		return xml;
	}

	public String getDownloadDir() {
		return downloadDir;
	}

	public void setDownloadDir(String downloadDir) {
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

	public void setPort(String port) throws Exception {
		int intPort = 0;

		try {
			intPort = Integer.parseInt(port);
		} catch (Exception e) {
			throw new Exception("Port musí být celé číslo!");
		}

		if ((intPort < 1) || (intPort > 10000)) {
			throw new Exception("Číslo portu musí být větší než 0 a menší než 10000!");
		}

		setPort(intPort);
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getHorizontalSplit() {
		return horizontalSplit;
	}

	public void setHorizontalSplit(int horizontalSplit) {
		this.horizontalSplit = horizontalSplit;
	}

	public int getVerticalSplit() {
		return verticalSplit;
	}

	public void setVerticalSplit(int verticalSplit) {
		this.verticalSplit = verticalSplit;
	}

	public String[] getShareDirs() {
		String[] dirs = new String[shareDirs.size()];

		for (int i = 0; i < shareDirs.size(); i++) {
			dirs[i] = shareDirs.get(i);
		}

		return dirs;
	}

	public boolean isShareDir(String dir) {
		for (int i = 0; i < shareDirs.size(); i++) {
			if (dir.startsWith(shareDirs.get(i))) {
				return true;
			}
		}

		return false;
	}

	public void addShareDir(String dir) throws Exception {
		shareDirs.add(dir);
	}

	public void clearShareDirs() {
		shareDirs.clear();
	}

	public static String encrypt(String text) {
		BASE64Encoder enc = new BASE64Encoder();
		try {
			return enc.encode(text.getBytes("UTF-8"));
		} catch (Exception e) {
			return null;
		}
	}

	public static String decrypt(String text) {
		BASE64Decoder dec = new BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(text), "UTF-8");
		}
		catch (Exception e) {
			return null;
		}
	}
}
