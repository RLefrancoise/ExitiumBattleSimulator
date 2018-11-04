package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	
	private MainPanel _mainPanel;
	
	private JMenuBar menu;
	private JMenu menuFichier;
	private JMenu menuAide;
	private JMenuItem itemAjouter;
	private JMenuItem itemOuvrir;
	private JMenuItem itemSauver;
	private JMenuItem itemSupprimer;
	private JMenuItem itemQuitter;
	
	private static MainFrame INSTANCE;
	
	private MainFrame(String title) {
		super(title);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setVisible(true);
		
		//menu
		this.menu = new JMenuBar();
		this.menuFichier = new JMenu("Build");
		this.menuAide = new JMenu("Aide");
		this.itemAjouter = new JMenuItem("Nouveau");
		this.itemOuvrir = new JMenuItem("Ouvrir");
		this.itemSauver = new JMenuItem("Sauvegarder");
		this.itemSupprimer = new JMenuItem("Supprimer");
		this.itemQuitter = new JMenuItem("Quitter");
		
		this.menuFichier.add(itemAjouter);
		this.menuFichier.add(itemOuvrir);
		this.menuFichier.add(itemSauver);
		this.menuFichier.add(itemSupprimer);
		this.menuFichier.add(itemQuitter);

		this.menu.add(menuFichier);
		this.menu.add(menuAide);
		
		//this.setJMenuBar(menu); //Met le barre de Menu
		
		//main panel
		_mainPanel = new MainPanel();
		this.setContentPane(_mainPanel);	//Indique le panel qui imbriquera tout les autres
	}
	
	public static MainFrame getInstance() {
		if(INSTANCE == null) INSTANCE = new MainFrame("Exitium Battle Simulator");
		
		return INSTANCE;
	}
}
