package fileshare.gui;

import fileshare.FileShare;
import fileshare.settings.Settings;
import fileshare.settings.Users;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

/**
 * Uživatelské rozhraní.
 *
 * @author Jakub Trmota
 */
public class FormMain extends JFrame {
	
	private JFrame formMain = null;

	private ControllerMain controllerMain = null;

	private DialogSearch dialogSearch = null;
	private DialogUsers dialogUsers = null;
	private DialogSettings dialogSettings = null;
	private DialogAbout dialogAbout = null;

	public static final String COMMAND_CONNECT = "connect";
	public static final String COMMAND_DISCONNECT = "disconnect";
	public static final String COMMAND_SEARCH = "search";
	public static final String COMMAND_USERS = "users";
	public static final String COMMAND_SETTINGS = "settings";
	public static final String COMMAND_END = "end";
	public static final String COMMAND_ABOUT = "about";

	public FormMain() throws HeadlessException {
		// DIALOGS
		dialogSearch = new DialogSearch(this, true);
		dialogUsers = new DialogUsers(this, true);
		dialogSettings = new DialogSettings(this, true);
		dialogAbout = new DialogAbout(this, true);

		// CONTROLLER
		controllerMain = new ControllerMain(this);

		// LAYOUT
		getContentPane().setLayout(new BorderLayout());

		// MENU
		JMenuBar menuBar = new JMenuBar();

		JMenu menuFile = new JMenu("Soubor");
		menuFile.setMnemonic(KeyEvent.VK_S);

		JMenuItem menuFileConnect = new JMenuItem("Připojit", KeyEvent.VK_P);
		menuFileConnect.setActionCommand(COMMAND_CONNECT);
		menuFileConnect.addActionListener(controllerMain);
		menuFile.add(menuFileConnect);

		JMenuItem menuFileDisconnect = new JMenuItem("Odpojit", KeyEvent.VK_O);
		menuFileDisconnect.setActionCommand(COMMAND_DISCONNECT);
		menuFileDisconnect.addActionListener(controllerMain);
		menuFile.add(menuFileDisconnect);

		menuFile.addSeparator();

		JMenuItem menuFileSearch = new JMenuItem("Hledat...", KeyEvent.VK_N);
		menuFileSearch.setActionCommand(COMMAND_SEARCH);
		menuFileSearch.addActionListener(controllerMain);
		menuFile.add(menuFileSearch);
		
		JMenuItem menuFileUsers = new JMenuItem("Uživatelé...", KeyEvent.VK_C);
		menuFileUsers.setActionCommand(COMMAND_USERS);
		menuFileUsers.addActionListener(controllerMain);
		menuFile.add(menuFileUsers);

		menuFile.addSeparator();

		JMenuItem menuFileSettings = new JMenuItem("Nastavení...", KeyEvent.VK_N);
		menuFileSettings.setActionCommand(COMMAND_SETTINGS);
		menuFileSettings.addActionListener(controllerMain);
		menuFile.add(menuFileSettings);

		menuFile.addSeparator();

		JMenuItem menuFileEnd = new JMenuItem("Konec", KeyEvent.VK_K);
		menuFileEnd.setActionCommand(COMMAND_END);
		menuFileEnd.addActionListener(controllerMain);
		menuFile.add(menuFileEnd);

		JMenu menuHelp = new JMenu("Nápověda");
		menuHelp.setMnemonic(KeyEvent.VK_N);
		JMenuItem menuHelpAbout = new JMenuItem("O aplikaci...", KeyEvent.VK_O);
		menuHelpAbout.setActionCommand(COMMAND_ABOUT);
		menuHelpAbout.addActionListener(controllerMain);
		menuHelp.add(menuHelpAbout);

		menuBar.add(menuFile);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(menuHelp);

		// TOOLBAR
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		JButton toolBarConnect = new JButton();
		toolBarConnect.setActionCommand(COMMAND_CONNECT);
		toolBarConnect.setToolTipText("Připojit");
		toolBarConnect.addActionListener(controllerMain);
		toolBarConnect.setIcon(new ImageIcon(getClass().getResource("icon/icon-connect.png"), "Připojit"));
		toolBar.add(toolBarConnect);

		JButton toolBarDisconnect = new JButton();
		toolBarDisconnect.setActionCommand(COMMAND_DISCONNECT);
		toolBarDisconnect.setToolTipText("Odpojit");
		toolBarDisconnect.addActionListener(controllerMain);
		toolBarDisconnect.setIcon(new ImageIcon(getClass().getResource("icon/icon-disconnect.png"), "Odpojit"));
		toolBar.add(toolBarDisconnect);

		toolBar.addSeparator();

		JButton toolBarSearch = new JButton();
		toolBarSearch.setActionCommand(COMMAND_SEARCH);
		toolBarSearch.setToolTipText("Hledat...");
		toolBarSearch.addActionListener(controllerMain);
		toolBarSearch.setIcon(new ImageIcon(getClass().getResource("icon/icon-search.png"), "Hledat..."));
		toolBar.add(toolBarSearch);

		JButton toolBarUsers = new JButton();
		toolBarUsers.setActionCommand(COMMAND_USERS);
		toolBarUsers.setToolTipText("Uživatelé...");
		toolBarUsers.addActionListener(controllerMain);
		toolBarUsers.setIcon(new ImageIcon(getClass().getResource("icon/icon-users.png"), "Uživatelé..."));
		toolBar.add(toolBarUsers);

		toolBar.addSeparator();

		JButton toolBarSettings = new JButton();
		toolBarSettings.setActionCommand(COMMAND_SETTINGS);
		toolBarSettings.setToolTipText("Nastavení...");
		toolBarSettings.addActionListener(controllerMain);
		toolBarSettings.setIcon(new ImageIcon(getClass().getResource("icon/icon-settings.png"), "Nastavení..."));
		toolBar.add(toolBarSettings);

		JButton toolBarAbout = new JButton();
		toolBarAbout.setActionCommand(COMMAND_ABOUT);
		toolBarAbout.setToolTipText("O aplikaci...");
		toolBarAbout.addActionListener(controllerMain);
		toolBarAbout.setIcon(new ImageIcon(getClass().getResource("icon/icon-about.png"), "O aplikaci..."));
		toolBar.add(toolBarAbout);

		// COMPUTERS TREE
		JTree treeUsers = new JTree();
		treeUsers.setModel(Users.getUsers().getTreeModel());
		treeUsers.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		treeUsers.setRootVisible(false);
		treeUsers.setCellRenderer(new TreeRendererUsers());
		treeUsers.addTreeSelectionListener(controllerMain);

		JScrollPane computersScrollPane = new JScrollPane(treeUsers);
		computersScrollPane.setMinimumSize(new Dimension(200, 200));

		// FILES TABLE
		JTable filesTable = new JTable(new TableModelFiles());
		filesTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
		filesTable.setFillsViewportHeight(true);
		filesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		filesTable.getColumnModel().getColumn(1).setMaxWidth(200);

		JScrollPane filesScrollPane = new JScrollPane(filesTable);
		filesScrollPane.setMinimumSize(new Dimension(300, 200));

		// TRANSFER TABLE
		JTable transferTable = new JTable(new TableModelTransfer());
		transferTable.setPreferredScrollableViewportSize(new Dimension(800, 200));
		transferTable.setFillsViewportHeight(true);
		transferTable.setShowVerticalLines(false);
		transferTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		transferTable.getColumnModel().getColumn(0).setMaxWidth(25);
		transferTable.getColumnModel().getColumn(0).setCellRenderer(new IconRenderer());
		transferTable.getColumnModel().getColumn(3).setCellRenderer(new ProgressBarRenderer());
		transferTable.getColumnModel().getColumn(5).setMaxWidth(100);
    transferTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
    transferTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

		JScrollPane transferScrollPane = new JScrollPane(transferTable);
		transferScrollPane.setMinimumSize(new Dimension(800, 150));

		// SPLIT PANES
		JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, computersScrollPane, filesScrollPane);
		splitPaneHorizontal.setOneTouchExpandable(false);
		splitPaneHorizontal.setDividerLocation(250);
		splitPaneHorizontal.setPreferredSize(new Dimension(800, 400));
		splitPaneHorizontal.setMinimumSize(new Dimension(100, 50));

		JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPaneHorizontal, transferScrollPane);
		splitPaneVertical.setOneTouchExpandable(false);
		splitPaneVertical.setDividerLocation(600);

		JPanel panelTop = new JPanel(new BorderLayout());
		panelTop.add(menuBar, BorderLayout.PAGE_START);
		panelTop.add(toolBar, BorderLayout.PAGE_END);

		getContentPane().add(panelTop, BorderLayout.PAGE_START);
		getContentPane().add(splitPaneVertical, BorderLayout.CENTER);
	}

	public void showDialogSearch() {
		dialogSearch.setVisible(true);
	}

	public void showDialogUsers() {
		String[] usersList = Users.getUsers().getUsersArray();

		dialogUsers.getPanel().getTxtPort().setText(String.valueOf(Settings.DEFAULT_PORT));

		dialogUsers.getModelUsers().clear();
		for(String user : usersList) {
			dialogUsers.getModelUsers().addElement(user);
		}

		dialogUsers.setVisible(true);
	}

	public void showDialogSettings() {
		Settings settings = Settings.getSettings();

		dialogSettings.getPanel().getTextPort().setText(String.valueOf(settings.getPort()));
		dialogSettings.getPanel().getPassword().setText(settings.getPassword());

		String[] shareDirs = settings.getShareDirs();

		dialogSettings.getModelShareDirs().clear();
		for(String dir : shareDirs) {
			dialogSettings.getModelShareDirs().addElement(dir);
		}

		dialogSettings.getPanel().getCheckAutoUpload().setSelected(settings.isAutomaticUpload());
		dialogSettings.getPanel().getTextSaveDir().setText(settings.getDownloadDir());

		dialogSettings.setVisible(true);
	}

	public void showDialogAbout() {
		dialogAbout.setVisible(true);
	}

	public void setFormTitle() {
		setFormTitle("");
	}

	public void setFormTitle(String message) {
		if (formMain != null) {
			formMain.setTitle(FileShare.NAME + " (" + FileShare.VERSION + ")" + (!message.isEmpty() ? " - " + message : ""));
		}
	}

	public void createAndShow() {
		formMain = new JFrame();

		formMain.setIconImage(new ImageIcon(getClass().getResource("icon/icon-main.png")).getImage());

		formMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		formMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formMain.setContentPane(this.getContentPane());

		setFormTitle();

		formMain.setVisible(true);
	}

}
