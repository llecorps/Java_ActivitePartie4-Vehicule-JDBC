package fr.yoannroche.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ocr.observer.Observable;
import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOFactory;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.VehiculeDAO;
import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.option.Option;

public class ZAddVehicule extends JDialog {

	private Vehicule vehicule;
	private static final Logger logger = LogManager.getLogger();


	private JComboBox<String> marque , moteurL ;
	private JCheckBox optionL;
	private JTextField nom  ;
	JFormattedTextField prix = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JButton ok = new JButton ("OK");
	private JButton cancel = new JButton ("CANCEL");
	private List<Option> optionV = new ArrayList<Option>();
	private int moteurID = 0;
	private int marqueID = 0;




	public ZAddVehicule(JFrame parent, String title, boolean modal,Vehicule vehicule,  Observable obs) {
		super(parent,title,modal);
		this.vehicule = vehicule;
		this.setSize(new Dimension(650,315));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		this.initComponent(obs);
	}

	private void initComponent(final Observable obs) {

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
			marque = new JComboBox<String>();
			for(int i=0;i<3;i++) {
				marque.addItem(marqueDao.find(i).getNom());
			}

			marque.setBorder(BorderFactory.createTitledBorder(" Marque du Vehicule "));
			marque.setBackground(Color.white);
			marque.setPreferredSize(new Dimension(170,40));
			panMarque.add(marque);
			JPanel panMoteur = new JPanel();
			panMoteur.setBackground(Color.white);
			panMoteur.setPreferredSize(new Dimension(200,55));
			panMoteur.setBorder(BorderFactory.createLineBorder(Color.black));
			final DAO<Moteur> moteurDao = DAOFactory.getMoteurDAO();
			moteurL = new JComboBox<String>();

			for(int i =0;i<9;i++) {
				moteurL.addItem(moteurDao.find(i).getCylindre());
			}

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
			JPanel panOption = new JPanel();
			JPanel option = new JPanel();
			final JCheckBox option1 = new JCheckBox(optionDao.find(0).getNom());
			final JCheckBox option2 = new JCheckBox(optionDao.find(1).getNom());
			final JCheckBox option3 = new JCheckBox(optionDao.find(2).getNom());
			final JCheckBox option4 = new JCheckBox(optionDao.find(3).getNom());
			final JCheckBox option5 = new JCheckBox(optionDao.find(4).getNom());
			option.add(option1);
			option.add(option2);
			option.add(option3);
			option.add(option4);
			option.add(option5);		
			panOption.setBackground(Color.white);
			panOption.setPreferredSize(new Dimension(550,75));
			panOption.setBorder(BorderFactory.createLineBorder(Color.black));		
			option.setPreferredSize(new Dimension(520,60));	    
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
				public void actionPerformed(ActionEvent arg0) {  

					try {

						moteurID = moteurL.getSelectedIndex();
						marqueID = marque.getSelectedIndex();
						Double price = ((Number) prix.getValue()).doubleValue();			
						DAO<Vehicule> vehiculeDao = new VehiculeDAO(HsqldbConnection.getInstance());
						vehicule.setMarque(marqueDao.find(marqueID));
						vehicule.setMoteur(moteurDao.find(moteurID));
						vehicule.setPrix(price);
						vehicule.setNom(nom.getText());
						vehicule.setId(vehicule.getId());
						if(option1.isSelected()==true) {
							vehicule.addOption(optionDao.find(0));
						}
						if(option2.isSelected()==true) {
							vehicule.addOption(optionDao.find(1));
						}
						if(option3.isSelected()==true) {
							vehicule.addOption(optionDao.find(2));
						}
						if(option4.isSelected()==true) {
							vehicule.addOption(optionDao.find(3));
						}
						if(option5.isSelected()==true) {
							vehicule.addOption(optionDao.find(4));
						}

						vehicule = vehiculeDao.create(vehicule);

						obs.updateObservateur();   //Lancement de la méthode update de l'Observateur.

						setVisible(false);

						logger.info("Option(s) ajoutée(s) au véhicule : " + optionV);

					}catch(Exception arg1) {
						arg1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Vous devez entrer le nom du vehicule et son prix !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}   
			});

			option.setBackground(Color.white);
			panOption.add(option);
			JPanel content = new JPanel();
			content.add(panVehicule);
			content.add(panMarque);
			content.add(panMoteur);
			JPanel espace = new JPanel();
			espace.setPreferredSize(new Dimension (400,15));
			espace.setBackground(Color.getHSBColor(0.550f, 0.30f, 0.80f));
			espace.setBorder(BorderFactory.createLineBorder(Color.black));
			content.add(espace);
			content.add(panPrix);
			JPanel espace2 = new JPanel();
			espace2.setPreferredSize(new Dimension (400,15));
			espace2.setBackground(Color.getHSBColor(0.550f, 0.30f, 0.80f));
			espace2.setBorder(BorderFactory.createLineBorder(Color.black));
			content.add(espace2);
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




