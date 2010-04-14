package fileshare.gui;

import fileshare.settings.User;
import fileshare.settings.Users;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Renderer pro uživatele.
 *
 * @author Jakub Trmota
 */
public class TreeRendererUsers extends DefaultTreeCellRenderer {

	ImageIcon iconOffline = null;

	ImageIcon iconOnline = null;

	ImageIcon iconFolder = null;


	public TreeRendererUsers() {
		iconOffline = new ImageIcon(getClass().getResource("icon/icon-user-offline.png"));
		iconOnline = new ImageIcon(getClass().getResource("icon/icon-user-online.png"));
		iconFolder = new ImageIcon(getClass().getResource("icon/icon-folder.png"));
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		if (node.getDepth() == 0) { // Uživatel
			if (Users.getUsers().get(node).getStatus() == User.STATUS_OFFLINE) {
				setIcon(iconOffline);
			} else if(Users.getUsers().get(node).getStatus() == User.STATUS_ONLINE) {
				setIcon(iconOnline);
			}
		} else { // Složka
			setIcon(iconFolder);
		}
		return this;
	}
}
