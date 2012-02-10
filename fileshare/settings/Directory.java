package fileshare.settings;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Share directory.
 *
 * @author Jakub Trmota | Forrest79
 */
public class Directory extends DefaultMutableTreeNode {
	/**
	 * Files list.
	 */
	private ArrayList<OneFile> files = null;

	/**
	 * User with directory.
	 */
	private User user = null;

	/**
	 * Initialization directory.
	 *
	 * @param name
	 * @param user
	 */
	public Directory(String name, User user) {
		super(name);

		this.user = user;

		files = new ArrayList<OneFile>();
	}

	/**
	 * Add file to directory.
	 *
	 * @param name
	 * @param path
	 * @param size
	 * @param user
	 */
	public void addFile(String name, String path, long size, User user) {
		OneFile file = new OneFile(name, path, size, user);

		files.add(file);
	}

	/**
	 * Get files in directory.
	 *
	 * @return
	 */
	public OneFile[] getFiles() {
		OneFile[] fileArray = new OneFile[files.size()];

		for (int i = 0; i < files.size(); i++) {
			fileArray[i] = files.get(i);
		}

		return fileArray;
	}

	/**
	 * Get files in directory as list.
	 *
	 * @return
	 */
	public ArrayList<OneFile> getFilesList() {
		return files;
	}

	/**
	 * Get user.
	 *
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Get one file.
	 * 
	 * @param index
	 * @return
	 */
	public OneFile getFile(int index) {
		return files.get(index);
	}
}
