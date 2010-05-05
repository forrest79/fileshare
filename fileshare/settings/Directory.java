package fileshare.settings;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Adresář.
 *
 * @author Jakub Trmota
 */
public class Directory extends DefaultMutableTreeNode {

	private ArrayList<OneFile> files = null;

	private User user = null;

	public Directory(String name, User user) {
		super(name);

		this.user = user;

		files = new ArrayList<OneFile>();
	}

	public void addFile(String name, String path, long size, User user) {
		OneFile file = new OneFile(name, path, size, user);

		files.add(file);
	}

	public OneFile[] getFiles() {
		OneFile[] fileArray = new OneFile[files.size()];

		for (int i = 0; i < files.size(); i++) {
			fileArray[i] = files.get(i);
		}

		return fileArray;
	}

	public ArrayList<OneFile> getFilesList() {
		return files;
	}

	public User getUser() {
		return user;
	}

	public OneFile getFile(int index) {
		return files.get(index);
	}
}
