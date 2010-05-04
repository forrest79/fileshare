package fileshare.settings;

/**
 * Soubor.
 *
 * @author Jakub Trmota
 */
public class OneFile {

	private String name = "";
	private String path = "";
	private int size = 0;

	public OneFile(String name, String path, int size) {
		this.name = name;
		this.path = path;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "File{" + "name=" + name + ", path=" + path + ", size=" + size + '}';
	}
}