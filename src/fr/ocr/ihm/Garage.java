package fr.ocr.ihm;

import java.awt.BorderLayout;
import java.awt.Color;

import fr.ocr.observer.Observateur;
import fr.ocr.observer.Observable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ocr.ihm.listener.ButtonListener;
import fr.ocr.ihm.listener.NewVehiculeListener;
import fr.ocr.ihm.listener.ViewMenuListener;
import fr.ocr.observer.Observable;
import fr.ocr.observer.Observateur;
import fr.ocr.sql.DAOTableFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.HsqldbConnection;
import jdk.nashorn.internal.ir.BreakableNode;

public class Garage extends JFrame implements Observateur {

	//Les diff�rents objets de notre IHM
	//-- Les logs
		private static final Logger logger = LogManager.getLogger();
		private Garage garage;
	
	private JMenuBar bar = new JMenuBar();
	private JMenu menuVehicule = new JMenu("Vehicule");
	private JMenuItem menuVehiculeAjouter = new JMenuItem("Ajouter");
	private JMenuItem menuVehiculeVoir = new JMenuItem("Voir");
	
	private JMenu menuListeOption = new JMenu("Option Vehicule");
	private JMenuItem menuListeOptionVoir = new JMenuItem("Voir");

	private JMenu menuMarque = new JMenu("Marque");
	private JMenuItem menuMarqueVoir = new JMenuItem("Voir");

	private JMenu menuMoteur = new JMenu("Moteur");
	private JMenuItem menuMoteurVoir = new JMenuItem("Voir");

	private JMenu menuOption = new JMenu("Option");
	private JMenuItem menuOptionVoir = new JMenuItem("Voir");

	private JMenu menuTypemoteur = new JMenu("Type de moteur");
	private JMenuItem menuTypemoteurVoir = new JMenuItem("Voir");

	private JTable tableau;
	private JButton change = new JButton("Changer la taille");
	// Contenu de notre combo
	private String[] comboData = { "Trés bien", "Bien", "Mal" };

	private JPanel contentPane = new JPanel();
	private ButtonListener delete;

	public Garage() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTable");
		this.setSize(800, 400);
		// Donn�es de notre tableau

		this.getContentPane()
				.add(new JScrollPane(DAOTableFactory.getTable(
						HsqldbConnection.getInstance(), DatabaseTable.VEHICULE)),
						BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		
		initMenu();
		logger.info("Lancement du garage");	    

	}

	/**
	 * Méthode qui initialise les points de menu
	 */
	private void initMenu() {
		menuVehicule.add(menuVehiculeVoir);
		menuVehicule.add(menuVehiculeAjouter);
		menuVehiculeAjouter.addActionListener(new NewVehiculeListener(this));
		menuVehiculeAjouter.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menuVehiculeVoir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menuVehiculeVoir.addActionListener(new ViewMenuListener(this,
				DatabaseTable.VEHICULE));
		menuVehicule.setMnemonic('v');
		
		menuListeOption.add(menuListeOptionVoir);
		menuListeOptionVoir.addActionListener(new ViewMenuListener(this,
				DatabaseTable.VEHICULE_OPTION));
		

		menuMarque.add(menuMarqueVoir);
		menuMarque.setMnemonic('a');
		menuMarqueVoir.addActionListener(new ViewMenuListener(this,
				DatabaseTable.MARQUE));

		menuMoteur.add(menuMoteurVoir);
		menuMoteur.setMnemonic('m');
		menuMoteurVoir.addActionListener(new ViewMenuListener(this,
				DatabaseTable.MOTEUR));

		menuOption.add(menuOptionVoir);
		menuOption.setMnemonic('o');
		menuOptionVoir.addActionListener(new ViewMenuListener(this,
				DatabaseTable.OPTION));

		menuTypemoteur.add(menuTypemoteurVoir);
		menuTypemoteur.setMnemonic('t');
		menuTypemoteurVoir.addActionListener(new ViewMenuListener(this,
				DatabaseTable.TYPEMOTEUR));

		bar.add(menuVehicule);
		bar.add(menuListeOption);
		bar.add(menuMarque);
		bar.add(menuMoteur);
		bar.add(menuOption);
		bar.add(menuTypemoteur);

		
		
		this.setJMenuBar(bar);
	}
	  public static void main(String[] args) {
			Garage g = new Garage();
			g.setVisible(true);
			
			
		}
	  public void update() {
		  menuVehicule.doClick();
		  logger.info("Méthode update du pattern Observer");
	  }

	
	  

	
}
