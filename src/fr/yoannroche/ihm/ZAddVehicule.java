package fr.yoannroche.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ocr.ihm.Garage;
import fr.ocr.observer.Observateur;
import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.VehiculeDAO;
import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.option.Option;
import javafx.scene.control.CheckBox;

public class ZAddVehicule extends JDialog {

	private Vehicule vehicule;
	private static final Logger logger = LogManager.getLogger();


	private JLabel vehiculeLabel, marqueLabel, moteurLabel, optionLabel , prixLabel ;



	private JComboBox marque , moteurL ;
	private JTextField nom  ;
	JFormattedTextField prix = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JButton ok = new JButton ("OK");
	private JButton cancel = new JButton ("CANCEL");
	private boolean sendData;
	private List<Option> optionV = new ArrayList<Option>();
	private List<Double> options;

	private Moteur moteurV = new Moteur();
	private Marque marqueV = new Marque();
	private int moteurID = 0;
	private int marqueID = 0;


	public ZAddVehicule(JFrame parent, String title, boolean modal,Vehicule vehicule) {
		super(parent,title,modal);
		this.vehicule = vehicule;
		this.setSize(new Dimension(650,280));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		this.initComponent();
	}

	private void initComponent() {

		try {


			JPanel panVehicule = new JPanel();
			panVehicule.setBackground(Color.white);
			panVehicule.setPreferredSize(new Dimension(200,55));
			panVehicule.setBorder(BorderFactory.createLineBorder(Color.black));
			nom = new JTextField();
			nom.setBorder(BorderFactory.createTitledBorder(" Nom du Vehicule "));
			nom.setBackground(Color.white);
			nom.setPreferredSize(new Dimension(150,40));
			panVehicule.add(nom);

			JPanel panMarque = new JPanel();
			panMarque.setBackground(Color.white);
			panMarque.setPreferredSize(new Dimension(200,55));
			panMarque.setBorder(BorderFactory.createLineBorder(Color.black));

			final DAO<Marque> marqueDao = DAOFactory.getMarqueDAO();
			final Marque marque1 = marqueDao.find(0);
			final Marque marque2 = marqueDao.find(1);
			final Marque marque3 = marqueDao.find(2);


			marque = new JComboBox();

			marque.addItem(marque1.getNom());
			marque.addItem(marque2.getNom());
			marque.addItem(marque3.getNom());
			marque.setBorder(BorderFactory.createTitledBorder(" Marque du Vehicule "));
			marque.setBackground(Color.white);
			marque.setPreferredSize(new Dimension(170,40));
			panMarque.add(marque);

			JPanel panMoteur = new JPanel();
			panMoteur.setBackground(Color.white);
			panMoteur.setPreferredSize(new Dimension(200,55));
			panMoteur.setBorder(BorderFactory.createLineBorder(Color.black));

			final DAO<Moteur> moteurDao = DAOFactory.getMoteurDAO();

			final Moteur moteur1 = moteurDao.find(0);
			final Moteur moteur2 = moteurDao.find(1);
			final Moteur moteur3 = moteurDao.find(2);
			final Moteur moteur4 = moteurDao.find(3);
			final Moteur moteur5 = moteurDao.find(4);
			final Moteur moteur6 = moteurDao.find(5);
			final Moteur moteur7 = moteurDao.find(6);
			final Moteur moteur8 = moteurDao.find(7);
			final Moteur moteur9 = moteurDao.find(8);


			moteurL = new JComboBox();
			moteurL.addItem(moteur1.getCylindre());
			moteurL.addItem(moteur2.getCylindre());
			moteurL.addItem(moteur3.getCylindre());
			moteurL.addItem(moteur4.getCylindre());
			moteurL.addItem(moteur5.getCylindre());
			moteurL.addItem(moteur6.getCylindre());
			moteurL.addItem(moteur7.getCylindre());
			moteurL.addItem(moteur8.getCylindre());
			moteurL.addItem(moteur9.getCylindre());

			moteurL.setBorder(BorderFactory.createTitledBorder(" Marque du Vehicule "));
			moteurL.setBackground(Color.white);
			moteurL.setPreferredSize(new Dimension(170,40));
			panMoteur.add(moteurL);

			JPanel panPrix = new JPanel();
			panPrix.setBackground(Color.white);
			panPrix.setPreferredSize(new Dimension(280,55));
			panPrix.setBorder(BorderFactory.createLineBorder(Color.black));
			prix = new JFormattedTextField(NumberFormat.getNumberInstance());;
			prix.setBorder(BorderFactory.createTitledBorder(" Prix du Vehicule "));
			prix.setBackground(Color.white);
			prix.setPreferredSize(new Dimension(250,40));
			panPrix.add(prix);

			final DAO<Option> optionDao = DAOFactory.getOptionDAO();
			final Option optionL1 = optionDao.find(0);
			final Option optionL2 = optionDao.find(1);
			final Option optionL3 = optionDao.find(2);
			final Option optionL4 = optionDao.find(3);
			final Option optionL5 = optionDao.find(4);

			JPanel panOption = new JPanel();
			panOption.setBackground(Color.white);
			panOption.setPreferredSize(new Dimension(550,75));
			panOption.setBorder(BorderFactory.createLineBorder(Color.black));
			JPanel option = new JPanel();

			final JCheckBox option1 = new JCheckBox(optionL1.getNom());
			option1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {   
					if(option1.isSelected()==true) {
				
					option1.setBackground(Color.getHSBColor(0.550f, 0.50f, 0.95f));
					}
					if(option1.isSelected()==false) {
						option1.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
						
					}



				}
			});

			final JCheckBox option2 = new JCheckBox(optionL2.getNom());

			option2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {   
					if(option2.isSelected()==true) {

						option2.setBackground(Color.getHSBColor(0.550f, 0.50f, 0.95f));
					
					}

					if(option2.isSelected()==false) {
						option2.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
					}
				}

			});
			final JCheckBox option3 = new JCheckBox(optionL3.getNom());
			option3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {  
					if(option3.isSelected()==true) {
						option3.setBackground(Color.getHSBColor(0.550f, 0.50f, 0.95f));
					}
					if(option3.isSelected()==false) {
						option3.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
					}

				}
			});

			final JCheckBox option4 = new JCheckBox(optionL4.getNom());
			option4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {  
					if(option4.isSelected()==true) {
						option4.setBackground(Color.getHSBColor(0.550f, 0.50f, 0.95f));
					}
					if(option4.isSelected()==false) {
						option4.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
					}

				}
			});

			final JCheckBox option5 = new JCheckBox(optionL5.getNom());
			option5.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) { 
					if(option5.isSelected()==true) {
						
						option5.setBackground(Color.getHSBColor(0.550f, 0.50f, 0.95f));
					}
					if(option5.isSelected()==false) {
						option5.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
					}

				}
			});

			option.add(option1);
			option1.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
			option.add(option2);
			option2.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
			option.add(option3);
			option3.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
			option.add(option4);
			option4.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
			option.add(option5);
			option5.setBackground(Color.getHSBColor(0.600f, 0.10f, 0.90f));
			option.setBorder(BorderFactory.createTitledBorder(" Options Disponibles "));
			cancel.setPreferredSize(new Dimension(100,30));
			cancel.setBorder(BorderFactory.createLineBorder(Color.black));
			cancel.setBackground(Color.getHSBColor(0.126f, 0.45f, 0.94f));
			cancel.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);	
				}

			});
			ok.setPreferredSize(new Dimension(100,30));
			ok.setBorder(BorderFactory.createLineBorder(Color.black));
			ok.setBackground(Color.getHSBColor(0.246f, 0.45f, 0.94f));
			ok.addActionListener(new ActionListener(){
				private Garage garage;
				public void actionPerformed(ActionEvent arg0) {  

					try {
						
						if(option1.isSelected()==true) {
							
							vehicule.addOption(optionDao.find(0));
							optionV.add(optionDao.find(0));
							}
						if(option2.isSelected()==true) {

							vehicule.addOption(optionDao.find(1));
							optionV.add(optionDao.find(1));
						}
							if(option3.isSelected()==true) {
								optionV.add(optionDao.find(2));
								vehicule.addOption(optionDao.find(2));
							}
							if(option4.isSelected()==true) {
								optionV.add(optionDao.find(3));
								vehicule.addOption(optionDao.find(3));
							
							}
						
						
						if(option5.isSelected()==true) {
							vehicule.addOption(optionDao.find(4));
							optionV.add(optionDao.find(4));
						}

						moteurID = moteurL.getSelectedIndex();
						marqueID = marque.getSelectedIndex();
						Double price = ((Number) prix.getValue()).doubleValue();
						
						



						DAO<Vehicule> vehiculeDao = new VehiculeDAO(HsqldbConnection.getInstance());
						vehicule.setMarque(marqueDao.find(marqueID));
						vehicule.setMoteur(moteurDao.find(moteurID));
						vehicule.setPrix(price);
						vehicule.setNom(nom.getText());
						vehicule.setId(vehicule.getId());
						
						vehicule = vehiculeDao.create(vehicule);


						setVisible(false);


						logger.info("Option(s) ajoutée(s) au véhicule : " + optionV);

					}catch(Exception arg1) {
						JOptionPane jop3;
						jop3 = new JOptionPane();
						jop3.showMessageDialog(null, "Vous devez entrer le nom du vehicule et son prix !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}


				}   

			});

			option.setBackground(Color.white);
			panOption.add(option);



			JPanel content = new JPanel();
			content.add(panVehicule);
			content.add(panMarque);
			content.add(panMoteur);
			content.add(panPrix);
			content.add(panOption);
			content.add(ok);
			content.add(cancel);
			content.setBackground(Color.getHSBColor(0.550f, 0.40f, 0.90f));

			this.getContentPane().add(content,BorderLayout.CENTER);
			this.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();

		} 


	}

}




