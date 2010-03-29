package fileshare.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Dialog pro hledání souborů.
 *
 * @author Jakub Trmota
 */
public class DialogSearch extends JDialog {
	public DialogSearch(Frame owner, boolean modal) {
		super(owner, modal);

		setSize(500, 400);
		setResizable(false);
		setLocationRelativeTo(null);

		setTitle("Hledat");

		setLayout(new BorderLayout());

		JTable filesTable = new JTable(new TableModelFiles());
		filesTable.setFillsViewportHeight(true);
		filesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		filesTable.getColumnModel().getColumn(1).setMaxWidth(200);

		JScrollPane filesScrollPane = new JScrollPane(filesTable);

		add(filesScrollPane, BorderLayout.CENTER);

		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Hledat:"));

		JTextField textHledat = new JTextField(26);
		top.add(textHledat);

		JButton buttonSearch = new JButton("Hledat");
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
}
