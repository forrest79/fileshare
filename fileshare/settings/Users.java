package fileshare.settings;

import fileshare.FileShare;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Users (singleton).
 *
 * @author Jakub Trmota
 */
public class Users {

	private static Users usersInstance = null;

	private File fileUsers = null;

	private ArrayList<User> userList = null;

	private DefaultTreeModel treeModel = null;

	private DefaultMutableTreeNode rootNode = null;

	private ArrayList<OneFile> searchList = null;

	private Users() {
		fileUsers = new File(FileShare.getAppDir() + "users.ini");

		userList = new ArrayList<User>();

		rootNode = new DefaultMutableTreeNode("Uživatelé");
		treeModel = new DefaultTreeModel(rootNode);

		searchList = new ArrayList<OneFile>();
	}

	public static Users getUsers() {
		if (usersInstance == null) {
			usersInstance = new Users();
		}

		return usersInstance;
	}

	public boolean loadFromFile() {
		if (fileUsers.exists()) {
			BufferedReader input = null;
			try {
				input = new BufferedReader(new FileReader(fileUsers));

				String line = "";

				String name = "";
				String address = "";
				int port = 0;
				String password = "";
				while ((line = input.readLine()) != null) {
					line = line.trim();

					if (line.startsWith("#")) { // Komentar
						continue;
					}

					if (line.equalsIgnoreCase("[user]")) {
						if (!name.isEmpty() && !address.isEmpty() && (port > 0) && !password.isEmpty()) {
							addUser(new User(name, address, port, password));
						}

						name = "";
						address = "";
						port = 0;
						password = "";
					}

					String[] params = line.split("=", 2);

					if (params.length >= 2) {
						String key = params[0].trim().toLowerCase();
						String value = params[1].trim();

						if (key.equals("name")) {
							name = value;
						} else if (key.equals("address")) {
							address = value;
						} else if (key.equals("port")) {
							try {
								port = Integer.parseInt(value);
							} catch (Exception e) {
								if (FileShare.DEBUG) {
									System.err.println("Port error value: " + e.getMessage());
								}

								continue;
							}
						} else if(key.equals("password")) {
							password = Settings.decrypt(value);
						}
					}
				}
				if (!name.isEmpty() && !address.isEmpty() && (port > 0) && !password.isEmpty()) {
					addUser(new User(name, address, port, password));
				}
			} catch (IOException e) {
				if (FileShare.DEBUG) {
					System.err.println("Settings load error: " + e.getMessage());
				}
			} finally {
				try {
					input.close();
				} catch (IOException ex) {
					Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
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
			output = new BufferedWriter(new FileWriter(fileUsers));

			for (int i = 0; i < userList.size(); i++) {
				User user = userList.get(i);

				output.write("[User]" + FileShare.NL);
				output.write("Name=" + user.getName() + FileShare.NL);
				output.write("Address=" + user.getAddress() + FileShare.NL);
				output.write("Port=" + String.valueOf(user.getPort()) + FileShare.NL);
				output.write("Password=" + Settings.encrypt(user.getPassword()) + FileShare.NL);
				output.write(FileShare.NL);
			}
		} catch (IOException e) {
			if (FileShare.DEBUG) {
				System.err.println("Users write error: " + e.getMessage());
			}
		} finally {
			try {
				output.close();
			} catch (IOException ex) {
				System.err.println("Users write error: " + ex.getMessage());
			}
		}

		return true;
	}

	public boolean testUser(String name, String address, String port) throws Exception {
		if (name.isEmpty()) {
			throw new Exception("Musíte zadat název!");
		}

		if (address.isEmpty()) {
			throw new Exception("Musíte zadat adresu!");
		}

		int intPort = 0;

		try {
			intPort = Integer.parseInt(port);
		} catch (Exception e) {
			throw new Exception("Port musí být celé číslo!");
		}

		if ((intPort < 1) || (intPort > 10000)) {
			throw new Exception("Číslo portu musí být větší než 0 a menší než 10000!");
		}

		return true;
	}

	public void addUser(User user) {
		userList.add(user);

		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(user.getName());
		treeModel.insertNodeInto(childNode, rootNode, rootNode.getChildCount());

		reloadTreeView();
	}

	public User get(int index) {
		return userList.get(index);
	}

	public User get(DefaultMutableTreeNode node) {
		return get(rootNode.getIndex(node));
	}

	public void remove(int index) {
		userList.remove(index);

		treeModel.removeNodeFromParent((DefaultMutableTreeNode) rootNode.getChildAt(index));

		reloadTreeView();
	}

	public void update(int index) {
		treeModel.removeNodeFromParent((DefaultMutableTreeNode) rootNode.getChildAt(index));

		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(get(index).getName());
		treeModel.insertNodeInto(childNode, rootNode, index);

		reloadTreeView();
	}

	public String[] getUsersArray() {
		String[] users = new String[userList.size()];

		for (int i = 0; i < userList.size(); i++) {
			users[i] = userList.get(i).toString();
		}

		return users;
	}

	public ArrayList<User> getUsersList() {
		return userList;
	}

	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	public void reloadTreeView(int userIndex) {
		treeModel.reload(rootNode.getChildAt(userIndex));
	}

	public void reloadTreeView() {
		treeModel.reload();
	}

	public void parseShareDirsXml(int userIndex, String xml) {
		if (xml.isEmpty()) {
			return;
		}

		User user = userList.get(userIndex);
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource inStream = new InputSource();
			inStream.setCharacterStream(new StringReader(xml));

			Document doc = builder.parse(inStream);

			NodeList nodes = doc.getElementsByTagName("share").item(0).getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) rootNode.getChildAt(userIndex);
				Directory dir = new Directory(nodes.item(i).getAttributes().getNamedItem("name").getNodeValue(), user);

				treeModel.insertNodeInto(dir, node, node.getChildCount());

				parseDir(dir, nodes.item(i), user);
			}
		} catch (Exception e) {
			if (FileShare.DEBUG) {
				System.err.println(e);
			}
		}
	}

	private void parseDir(Directory dir, Node node, User user) {
		NodeList nodes = node.getChildNodes();

		for (int i = 0; i < nodes.getLength(); i++) {
			Node subNode = nodes.item(i);

			if (subNode.getNodeName().equalsIgnoreCase("dir")) {
				Directory subDir = new Directory(subNode.getAttributes().getNamedItem("name").getNodeValue(), user);

				treeModel.insertNodeInto(subDir, dir, dir.getChildCount());

				parseDir(subDir, subNode, user);
			} else if (subNode.getNodeName().equalsIgnoreCase("file")) {
				dir.addFile(subNode.getAttributes().getNamedItem("name").getNodeValue(), subNode.getAttributes().getNamedItem("path").getNodeValue(), Long.parseLong(subNode.getAttributes().getNamedItem("size").getNodeValue()), user);
			}
		}
	}

	public void removeDirsFromTree(int index) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) rootNode.getChildAt(index);

		while (node.getChildCount() > 0) {
			treeModel.removeNodeFromParent((DefaultMutableTreeNode) node.getChildAt(0));
		}
	}

	public void search(String search, DefaultTableModel defaultTableModel) {
		defaultTableModel.setRowCount(0);
		searchList.clear();

		for (int i = 0; i < rootNode.getChildCount(); i++) {
			searchDir(search, (DefaultMutableTreeNode) rootNode.getChildAt(i));
		}

		for (int i = 0; i < searchList.size(); i++) {
			OneFile file = searchList.get(i);
			defaultTableModel.addRow(new Object[] {file.getUser().getName(), file.getName(), String.valueOf(file.getSize() / 1024) + " kB"});
		}
	}

	private void searchDir(String search, DefaultMutableTreeNode node) {
		if (node instanceof Directory) {
			Directory dir = (Directory) node;
			ArrayList<OneFile> files = dir.getFilesList();

			for (int i = 0; i < files.size(); i++) {
				if (files.get(i).getName().toLowerCase().contains(search.toLowerCase())) {
					searchList.add(files.get(i));
				}
			}
		}

		for (int i = 0; i < node.getChildCount(); i++) {
			searchDir(search, (DefaultMutableTreeNode) node.getChildAt(i));
		}
	}

	public OneFile getSearch(int index) {
		return searchList.get(index);
	}
}
