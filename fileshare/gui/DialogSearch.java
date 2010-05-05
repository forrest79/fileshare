package fileshare.gui;

import fileshare.net.Server;
import fileshare.settings.Users;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * Dialog pro hledání souborů.
 *
 * @author Jakub Trmota
 */
public class DialogSearch extends JDialog implements MouseListener {

	private TableModelSearch tableModelSearch = null;

	public DialogSearch(Frame owner, boolean modal) {
		super(owner, modal);

		setSize(500, 400);
		setResizable(false);
		setLocationRelativeTo(null);

		setTitle("Hledat");

		setLayout(new BorderLayout());

		tableModelSearch = new TableModelSearch();

		JTable filesTable = new JTable(tableModelSearch);
		filesTable.setFillsViewportHeight(true);
		filesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		filesTable.getTableHeader().setReorderingAllowed(false);

		filesTable.getColumnModel().getColumn(2).setMaxWidth(200);

		filesTable.addMouseListener(this);

		JScrollPane filesScrollPane = new JScrollPane(filesTable);

		add(filesScrollPane, BorderLayout.CENTER);

		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Hledat:"));

		final JTextField txtSearch = new JTextField(26);
		top.add(txtSearch);

		JButton buttonSearch = new JButton("Hledat");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Users.getUsers().search(txtSearch.getText(), tableModelSearch);
			}
		});
		top.add(buttonSearch);

		JButton buttonClose = new JButton("Zavřít");
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		top.add(buttonClose);

		add(top, BorderLayout.PAGE_START);
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable) e.getSource();

			Server.getServer().download(Users.getUsers().getSearch(target.getSelectedRow()));
		}
	}

	public void mousePressed(MouseEvent e) {
		//
	}

	public void mouseReleased(MouseEvent e) {
		//
	}

	public void mouseEntered(MouseEvent e) {
		//
	}

	public void mouseExited(MouseEvent e) {
		//
	}
}
