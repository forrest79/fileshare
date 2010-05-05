package fileshare.settings;

/**
 * Soubor.
 *
 * @author Jakub Trmota
 */
public class OneFile {

	private String name = "";
	private String path = "";
	private long size = 0;
	private User user = null;

	public OneFile(String name, String path, long size, User user) {
		this.name = name;
		this.path = path;
		this.size = size;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public long getSize() {
		return size;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "File{" + "name=" + name + ", path=" + path + ", size=" + size + '}';
	}
}