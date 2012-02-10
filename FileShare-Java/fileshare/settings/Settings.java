package fileshare.settings;

import fileshare.FileShare;
import java.io.*;
import java.util.ArrayList;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Settings, save to file, load from file.
 *
 * @author Jakub Trmota | Forrest79
 */
public class Settings {
	/**
	 * Default application port.
	 */
	public static final int DEFAULT_PORT = 3278;

	/**
	 * Singleton - settings instance.
	 */
	private static Settings settingsInstance = null;

	/**
	 * File with settings.
	 */
	private File fileSettings = null;

	/**
	 * File with sharing paths.
	 */
	private File fileShare = null;

	/**
	 * Application port.
	 */
	private int port = DEFAULT_PORT;

	/**
	 * Actual password.
	 */
	private String password = "";

	/**
	 * Actual download dir.
	 */
	private String downloadDir = "";

	/**
	 * Main form horizontal split.
	 */
	private int horizontalSplit = 250;

	/**
	 * Main form vertical split.
	 */
	private int verticalSplit = 600;

	/**
	 * Share dirs list.
	 */
	private ArrayList<String> shareDirs = null;

	/**
	 * Initialize settings.
	 */
	private Settings() {
		fileSettings = new File(FileShare.getAppDir() + "settings.ini");
		fileShare = new File(FileShare.getAppDir() + "share.xml");

		downloadDir = FileShare.getAppDir();

		shareDirs = new ArrayList<String>();
	}

	/**
	 * Singleton - getSettings.
	 *
	 * @return
	 */
	public static Settings getSettings() {
		if (settingsInstance == null) {
			settingsInstance = new Settings();
		}

		return settingsInstance;
	}

	/**
	 * Load settings from file.
	 *
	 * @return
	 */
	public boolean loadFromFile() {
		if (fileSettings.exists()) {
			BufferedReader input = null;
			try {
				input = new BufferedReader(new FileReader(fileSettings));

				String line;
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
							password = Settings.decode(value);
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

	/**
	 * Save settings to file.
	 *
	 * @return
	 */
	public boolean saveToFile() {
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(fileSettings));

			output.write("Port=" + String.valueOf(port) + FileShare.NL);
			output.write("Password=" + Settings.encode(password) + FileShare.NL);
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

	/**
	 * Generate XML file with share directories.
	 *
	 * @return
	 */
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

	/**
	 * Return share directories XML.
	 *
	 * @return
	 */
	public String getShareDirsXml() {
		String xml = "";

		if (fileShare.exists()) {
			BufferedReader input = null;
			try {
				input = new BufferedReader(new FileReader(fileShare));

				String line;
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

	/**
	 * Return XML with directory listing.
	 *
	 * @param dir
	 * @return
	 */
	private String listDir(File dir) {
		if (!dir.isDirectory() || !dir.exists()) {
			return "";
		}

		FilenameFilter filter = new FilenameFilter() {
			@Override
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

	/**
	 * Get download directory.
	 *
	 * @return
	 */
	public String getDownloadDir() {
		return downloadDir;
	}

	/**
	 * Set download directory.
	 *
	 * @param downloadDir
	 */
	public void setDownloadDir(String downloadDir) {
		this.downloadDir = downloadDir;
	}

	/**
	 * Get password.
	 *
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password.
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get port.
	 *
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Check and set port.
	 *
	 * @param port
	 * @throws Exception
	 */
	public void setPort(String port) throws Exception {
		int intPort = 0;

		try {
			intPort = Integer.parseInt(port);
		} catch (NumberFormatException e) {
			throw new Exception("Port number must be integer!");
		}

		if ((intPort < 1) || (intPort > 10000)) {
			throw new Exception("Port number must be grater than 0 and lass than 10000!");
		}

		setPort(intPort);
	}

	/**
	 * Set port.
	 *
	 * @param port
	 */
	private void setPort(int port) {
		this.port = port;
	}

	/**
	 * Return main form horizontal split.
	 *
	 * @return
	 */
	public int getHorizontalSplit() {
		return horizontalSplit;
	}

	/**
	 * Set main form horizontal split.
	 *
	 * @param horizontalSplit
	 */
	public void setHorizontalSplit(int horizontalSplit) {
		this.horizontalSplit = horizontalSplit;
	}

	/**
	 * Get main form vertical split.
	 *
	 * @return
	 */
	public int getVerticalSplit() {
		return verticalSplit;
	}

	/**
	 * Set main form vertical split.
	 *
	 * @param verticalSplit
	 */
	public void setVerticalSplit(int verticalSplit) {
		this.verticalSplit = verticalSplit;
	}

	/**
	 * Get share directories array.
	 *
	 * @return
	 */
	public String[] getShareDirs() {
		String[] dirs = new String[shareDirs.size()];

		for (int i = 0; i < shareDirs.size(); i++) {
			dirs[i] = shareDirs.get(i);
		}

		return dirs;
	}

	/**
	 * Return true if directory is shared.
	 *
	 * @param dir
	 * @return
	 */
	public boolean isShareDir(String dir) {
		for (int i = 0; i < shareDirs.size(); i++) {
			if (dir.startsWith(shareDirs.get(i))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Add direcotory to share.
	 *
	 * @param dir
	 * @throws Exception
	 */
	public void addShareDir(String dir) throws Exception {
		shareDirs.add(dir);
	}

	/**
	 * Delete all shared directories.
	 */
	public void clearShareDirs() {
		shareDirs.clear();
	}

	/**
	 * Encode Base64 text.
	 *
	 * @param text
	 * @return
	 */
	public static String encode(String text) {
		BASE64Encoder enc = new BASE64Encoder();
		try {
			return enc.encode(text.getBytes("UTF-8"));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Decode Base64 text.
	 *
	 * @param text
	 * @return
	 */
	public static String decode(String text) {
		BASE64Decoder dec = new BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(text), "UTF-8");
		}
		catch (Exception e) {
			return null;
		}
	}
}
