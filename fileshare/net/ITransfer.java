package fileshare.net;

/**
 * Rozhraní pro upload a download souborů.
 *
 * @author Jakub Trmota
 */
public interface ITransfer {

	public int getCompleted();

	public void cancel();

}
