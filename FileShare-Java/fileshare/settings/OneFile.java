package fileshare.settings;

/**
 * File from directory.
 *
 * @author Jakub Trmota | Forrest79
 */
public class OneFile {
	/**
	 * File name.
	 */
	private String name = "";

	/**
	 * File path.
	 */
	private String path = "";

	/**
	 * File size in bytes.
	 */
	private long size = 0;

	/**
	 * File with user.
	 */
	private User user = null;

	/**
	 * Initialize file.
	 *
	 * @param name
	 * @param path
	 * @param size
	 * @param user
	 */
	public OneFile(String name, String path, long size, User user) {
		this.name = name;
		this.path = path;
		this.size = size;
		this.user = user;
	}

	/**
	 * Get name.
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get path.
	 *
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Get size.
	 *
	 * @return
	 */
	public long getSize() {
		return size;
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
	 * Get string represenatation.
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "File{" + "name=" + name + ", path=" + path + ", size=" + size + '}';
	}
}