package fileshare.gui;

import fileshare.FileShare;
import fileshare.net.Server;
import fileshare.net.Transfers;
import fileshare.settings.OneFile;
import fileshare.settings.Settings;
import fileshare.settings.Users;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.tree.TreeSelectionModel;

/**
 * GUI.
 *
 * @author Jakub Trmota | Forrest79
 */
public class FormMain extends JFrame {
	/**
	 * Connect command.
	 */
	public static final String COMMAND_CONNECT = "connect";

	/**
	 * Disconnect command.
	 */
	public static final String COMMAND_DISCONNECT = "disconnect";

	/**
	 * Search command.
	 */
	public static final String COMMAND_SEARCH = "search";

	/**
	 * Users command.
	 */
	public static final String COMMAND_USERS = "users";

	/**
	 * Settings command.
	 */
	public static final String COMMAND_SETTINGS = "settings";

	/**
	 * End command.
	 */
	public static final String COMMAND_END = "end";

	/**
	 * About command.
	 */
	public static final String COMMAND_ABOUT = "about";

	/**
	 * Main form frame.
	 */
	private JFrame formMain = null;

	/**
	 * Main controller.
	 */
	private ControllerMain controllerMain = null;

	/**
	 * Search dialog.
	 */
	private DialogSearch dialogSearch = null;

	/**
	 * Users dialog.
	 */
	private DialogUsers dialogUsers = null;

	/**
	 * Settings dialog.
	 */
	private DialogSettings dialogSettings = null;

	/**
	 * About dialog.
	 */
	private DialogAbout dialogAbout = null;

	/**
	 * Connect menu item.
	 */
	private JMenuItem menuFileConnect = null;

	/**
	 * Disconnect menu item.
	 */
	private JMenuItem menuFileDisconnect = null;

	/**
	 * Connect toolbar button.
	 */
	private JButton toolBarConnect = null;

	/**
	 * Disconnect toolbar button.
	 */
	private JButton toolBarDisconnect = null;

	/**
	 * Table with files.
	 */
	private TableModelFiles tableModelFiles = null;

	/**
	 * Table with transfers.
	 */
	private TableModelTransfer tableModelTransfer = null;

	/**
	 * Initailiza main form.
	 *
	 * @throws HeadlessException
	 */
	public FormMain() throws HeadlessException {
		// Models
		tableModelFiles = new TableModelFiles();
		tableModelTransfer = new TableModelTransfer();

		// Dialogs
		dialogSearch = new DialogSearch(this, true);
		dialogUsers = new DialogUsers(this, true);
		dialogSettings = new DialogSettings(this, true);
		dialogAbout = new DialogAbout(this, true);

		// Controller
		controllerMain = new ControllerMain(this);

		// Layout
		getContentPane().setLayout(new BorderLayout());

		// Menu
		JMenuBar menuBar = new JMenuBar();

		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_S);

		menuFileConnect = new JMenuItem("Connect", KeyEvent.VK_C);
		menuFileConnect.setActionCommand(COMMAND_CONNECT);
		menuFileConnect.addActionListener(controllerMain);
		menuFile.add(menuFileConnect);

		menuFileDisconnect = new JMenuItem("Disconnect", KeyEvent.VK_D);
		menuFileDisconnect.setActionCommand(COMMAND_DISCONNECT);
		menuFileDisconnect.addActionListener(controllerMain);
		menuFileDisconnect.setVisible(false);
		menuFile.add(menuFileDisconnect);

		menuFile.addSeparator();

		JMenuItem menuFileSearch = new JMenuItem("Search...", KeyEvent.VK_S);
		menuFileSearch.setActionCommand(COMMAND_SEARCH);
		menuFileSearch.addActionListener(controllerMain);
		menuFile.add(menuFileSearch);

		JMenuItem menuFileUsers = new JMenuItem("Users...", KeyEvent.VK_U);
		menuFileUsers.setActionCommand(COMMAND_USERS);
		menuFileUsers.addActionListener(controllerMain);
		menuFile.add(menuFileUsers);

		menuFile.addSeparator();

		JMenuItem menuFileSettings = new JMenuItem("Settings...", KeyEvent.VK_E);
		menuFileSettings.setActionCommand(COMMAND_SETTINGS);
		menuFileSettings.addActionListener(controllerMain);
		menuFile.add(menuFileSettings);

		menuFile.addSeparator();

		JMenuItem menuFileEnd = new JMenuItem("End", KeyEvent.VK_E);
		menuFileEnd.setActionCommand(COMMAND_END);
		menuFileEnd.addActionListener(controllerMain);
		menuFile.add(menuFileEnd);

		JMenu menuHelp = new JMenu("Help");
		menuHelp.setMnemonic(KeyEvent.VK_N);
		JMenuItem menuHelpAbout = new JMenuItem("About...", KeyEvent.VK_O);
		menuHelpAbout.setActionCommand(COMMAND_ABOUT);
		menuHelpAbout.addActionListener(controllerMain);
		menuHelp.add(menuHelpAbout);

		menuBar.add(menuFile);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(menuHelp);

		// Toolbar
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		toolBarConnect = new JButton();
		toolBarConnect.setActionCommand(COMMAND_CONNECT);
		toolBarConnect.setToolTipText("Connect");
		toolBarConnect.addActionListener(controllerMain);
		toolBarConnect.setIcon(new ImageIcon(getClass().getResource("icon/icon-connect.png"), "Connect"));
		toolBar.add(toolBarConnect);

		toolBarDisconnect = new JButton();
		toolBarDisconnect.setActionCommand(COMMAND_DISCONNECT);
		toolBarDisconnect.setToolTipText("Disconnect");
		toolBarDisconnect.addActionListener(controllerMain);
		toolBarDisconnect.setIcon(new ImageIcon(getClass().getResource("icon/icon-disconnect.png"), "Disconnect"));
		toolBarDisconnect.setVisible(false);
		toolBar.add(toolBarDisconnect);

		toolBar.addSeparator();

		JButton toolBarSearch = new JButton();
		toolBarSearch.setActionCommand(COMMAND_SEARCH);
		toolBarSearch.setToolTipText("Search...");
		toolBarSearch.addActionListener(controllerMain);
		toolBarSearch.setIcon(new ImageIcon(getClass().getResource("icon/icon-search.png"), "Search..."));
		toolBar.add(toolBarSearch);

		JButton toolBarUsers = new JButton();
		toolBarUsers.setActionCommand(COMMAND_USERS);
		toolBarUsers.setToolTipText("Users...");
		toolBarUsers.addActionListener(controllerMain);
		toolBarUsers.setIcon(new ImageIcon(getClass().getResource("icon/icon-users.png"), "Users..."));
		toolBar.add(toolBarUsers);

		toolBar.addSeparator();

		JButton toolBarSettings = new JButton();
		toolBarSettings.setActionCommand(COMMAND_SETTINGS);
		toolBarSettings.setToolTipText("Settings...");
		toolBarSettings.addActionListener(controllerMain);
		toolBarSettings.setIcon(new ImageIcon(getClass().getResource("icon/icon-settings.png"), "Settings..."));
		toolBar.add(toolBarSettings);

		JButton toolBarAbout = new JButton();
		toolBarAbout.setActionCommand(COMMAND_ABOUT);
		toolBarAbout.setToolTipText("About...");
		toolBarAbout.addActionListener(controllerMain);
		toolBarAbout.setIcon(new ImageIcon(getClass().getResource("icon/icon-about.png"), "About..."));
		toolBar.add(toolBarAbout);

		// Computers tree
		JTree treeUsers = new JTree();
		treeUsers.setModel(Users.getUsers().getTreeModel());
		treeUsers.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		treeUsers.setRootVisible(false);
		treeUsers.setCellRenderer(new TreeRendererUsers());
		treeUsers.addTreeSelectionListener(controllerMain);

		JScrollPane computersScrollPane = new JScrollPane(treeUsers);
		computersScrollPane.setMinimumSize(new Dimension(200, 200));

		// Files table
		JTable filesTable = new JTable(tableModelFiles);
		filesTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
		filesTable.setFillsViewportHeight(true);
		filesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		filesTable.getTableHeader().setReorderingAllowed(false);
		filesTable.addMouseListener(controllerMain);

		filesTable.getColumnModel().getColumn(1).setMaxWidth(200);

		JScrollPane filesScrollPane = new JScrollPane(filesTable);
		filesScrollPane.setMinimumSize(new Dimension(300, 200));

		// Transfer table
		JTable transferTable = new JTable(tableModelTransfer);
		transferTable.setPreferredScrollableViewportSize(new Dimension(800, 200));
		transferTable.setFillsViewportHeight(true);
		transferTable.setShowVerticalLines(false);
		transferTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		transferTable.getTableHeader().setReorderingAllowed(false);

		transferTable.getColumnModel().getColumn(0).setMaxWidth(25);
		transferTable.getColumnModel().getColumn(0).setCellRenderer(new IconRenderer());
		transferTable.getColumnModel().getColumn(3).setCellRenderer(new ProgressBarRenderer());
		transferTable.getColumnModel().getColumn(4).setMaxWidth(100);
		transferTable.getColumnModel().getColumn(4).setMinWidth(100);
		ButtonColumn buttonColumn = new ButtonColumn(transferTable, 4);

		JScrollPane transferScrollPane = new JScrollPane(transferTable);
		transferScrollPane.setMinimumSize(new Dimension(800, 150));

		// Split panes
		JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, computersScrollPane, filesScrollPane);
		splitPaneHorizontal.setOneTouchExpandable(false);
		splitPaneHorizontal.setDividerLocation(250);
		splitPaneHorizontal.setPreferredSize(new Dimension(800, 400));
		splitPaneHorizontal.setMinimumSize(new Dimension(100, 50));
		splitPaneHorizontal.addPropertyChangeListener("dividerLocation", controllerMain);
		splitPaneHorizontal.setDividerLocation(Settings.getSettings().getHorizontalSplit());

		JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPaneHorizontal, transferScrollPane);
		splitPaneVertical.setOneTouchExpandable(false);
		splitPaneVertical.setDividerLocation(600);
		splitPaneVertical.addPropertyChangeListener("dividerLocation", controllerMain);
		splitPaneVertical.setDividerLocation(Settings.getSettings().getVerticalSplit());

		JPanel panelTop = new JPanel(new BorderLayout());
		panelTop.add(menuBar, BorderLayout.PAGE_START);
		panelTop.add(toolBar, BorderLayout.PAGE_END);

		getContentPane().add(panelTop, BorderLayout.PAGE_START);
		getContentPane().add(splitPaneVertical, BorderLayout.CENTER);

		// Transfers
		Transfers.getTransfers().setTableModel(tableModelTransfer);
	}

	/**
	 * Clear files table and add files.
	 *
	 * @param files
	 */
	public void showFiles(OneFile[] files) {
		tableModelFiles.setNumRows(0);
		for (int i = 0; i < files.length; i++) {
			tableModelFiles.addRow(new Object[] {files[i].getName(), String.valueOf((files[i].getSize() / 1024)) + " kB"});
		}
	}

	/**
	 * Open seach dialog.
	 */
	public void showDialogSearch() {
		dialogSearch.setVisible(true);
	}

	/**
	 * Open users dialog.
	 */
	public void showDialogUsers() {
		String[] usersList = Users.getUsers().getUsersArray();

		dialogUsers.getPanel().getTxtPort().setText(String.valueOf(Settings.DEFAULT_PORT));

		dialogUsers.getModelUsers().clear();
		for(String user : usersList) {
			dialogUsers.getModelUsers().addElement(user);
		}

		dialogUsers.setVisible(true);
	}

	/**
	 * Open settings dialog.
	 */
	public void showDialogSettings() {
		Settings settings = Settings.getSettings();

		dialogSettings.getPanel().getTxtPort().setText(String.valueOf(settings.getPort()));
		dialogSettings.getPanel().getTxtPassword().setText(settings.getPassword());

		String[] shareDirs = settings.getShareDirs();

		dialogSettings.getModelShareDirs().clear();
		for(String dir : shareDirs) {
			dialogSettings.getModelShareDirs().addElement(dir);
		}

		dialogSettings.getPanel().getTxtSaveDir().setText(settings.getDownloadDir());

		dialogSettings.setVisible(true);
	}

	/**
	 * Open about dialog.
	 */
	public void showDialogAbout() {
		dialogAbout.setVisible(true);
	}

	/**
	 * Connect to network (only GUI).
	 */
	public void connect() {
		menuFileDisconnect.setVisible(true);
		menuFileConnect.setVisible(false);

		toolBarDisconnect.setVisible(true);
		toolBarConnect.setVisible(false);
	}

	/**
	 * Disconnect from network (only GUI).
	 */
	public void disconnect() {
		menuFileConnect.setVisible(true);
		menuFileDisconnect.setVisible(false);

		toolBarConnect.setVisible(true);
		toolBarDisconnect.setVisible(false);
	}

	/**
	 * Show error dialog.
	 *
	 * @param title
	 * @param message
	 */
	public void showErrorDialog(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Set blank form title.
	 */
	public void setFormTitle() {
		setFormTitle("");
	}

	/**
	 * Set form title.
	 *
	 * @param message
	 */
	public void setFormTitle(String message) {
		if (formMain != null) {
			formMain.setTitle(FileShare.NAME + " (" + FileShare.VERSION + ")" + (!message.isEmpty() ? " - " + message : ""));
		}
	}

	/**
	 * Create form a show it.
	 */
	public void createAndShow() {
		formMain = new JFrame();

		formMain.setIconImage(new ImageIcon(getClass().getResource("icon/icon-main.png")).getImage());

		formMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		formMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formMain.setContentPane(this.getContentPane());

		setFormTitle();

		Server.getServer().setFormMain(this);

		formMain.setVisible(true);
	}
}
