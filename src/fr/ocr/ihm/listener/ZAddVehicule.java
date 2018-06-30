package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOFactory;
import fr.ocr.sql.HsqldbConnection;
import voiture.Marque;
import voiture.moteur.Moteur;
import voiture.option.Option;
import fr.ocr.sql.VehiculeDAO;

public class ZAddVehicule extends JDialog {
	
	private JLabel vehiculeLabel, marqueLabel, moteurLabel, optionLabel , prixLabel ;
	private JRadioButton option1 , option2 , option3 , option4 , option5;
	private JComboBox<String> marque , moteurL ;
	private JTextField nom , prix ;
	private JButton ok = new JButton ("OK");
	private boolean sendData;
	private Option optionV;
	private Moteur moteurV;
	private Marque marqueV;


public ZAddVehicule(JFrame parent, String title, boolean modal) {
	super(parent,title,modal);
	this.setSize(new Dimension(650,270));
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.getContentPane().setBackground(Color.white);
	this.initComponent();
}

private void initComponent() {
		
	
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
	prix = new JTextField();
	prix.setBorder(BorderFactory.createTitledBorder(" Prix du Vehicule "));
	prix.setBackground(Color.white);
	prix.setPreferredSize(new Dimension(250,40));
	panPrix.add(prix);
	
    final DAO<Option> optionDao = DAOFactory.getOptionDAO();
	Option optionL1 = optionDao.find(0);
	Option optionL2 = optionDao.find(1);
	Option optionL3 = optionDao.find(2);
	Option optionL4 = optionDao.find(3);
	Option optionL5 = optionDao.find(4);
	
	JPanel panOption = new JPanel();
	panOption.setBackground(Color.white);
	panOption.setPreferredSize(new Dimension(550,75));
	panOption.setBorder(BorderFactory.createLineBorder(Color.black));
	JPanel option = new JPanel();
	option1 = new JRadioButton(optionL1.getNom());
	option2 = new JRadioButton(optionL2.getNom());
	option3 = new JRadioButton(optionL3.getNom());
	option4 = new JRadioButton(optionL4.getNom());
	option5 = new JRadioButton(optionL5.getNom());
	option.add(option1);
	option1.setBackground(Color.getHSBColor(0.550f, 0.20f, 0.95f));
	option.add(option2);
	option2.setBackground(Color.getHSBColor(0.550f, 0.20f, 0.95f));
	option.add(option3);
	option3.setBackground(Color.getHSBColor(0.550f, 0.20f, 0.95f));
	option.add(option4);
	option4.setBackground(Color.getHSBColor(0.550f, 0.20f, 0.95f));
	option.add(option5);
	option5.setBackground(Color.getHSBColor(0.550f, 0.20f, 0.95f));
	option.setBorder(BorderFactory.createTitledBorder(" Options Disponibles "));
	
	ok.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {        
	    		
	    		 try {
	    			  if(option1.isSelected()) {
	    				 optionV = optionDao.find(0);
	    			  }
	    			  if(option2.isSelected()) {
		    			 optionV = optionDao.find(1);
		    			  }
	    			  if(option3.isSelected()) {
		    			 optionV = optionDao.find(2);
		    			  }
	    			  if(option4.isSelected()) {
		    			 optionV = optionDao.find(3);
		    			  }
	    			  if(option5.isSelected()) {
		    			optionV = optionDao.find(4);
		    			  }
	    			  if(marque.getSelectedItem().equals(marque1)) {
		    				marqueV  = marqueDao.find(0);
		    			  }
	   		          
	    			  if(marque.getSelectedItem().equals(marque2)) {
		    				marqueV  = marqueDao.find(1);
		    			  }
	    			  if(marque.getSelectedItem().equals(marque3)) {
		    				marqueV  = marqueDao.find(2);
		    			  }
	    			  
	    			  if(moteurL.getSelectedItem().equals(moteur1)) {
		    				moteurV  = moteurDao.find(0);
		    			  }
	    			  if(moteurL.getSelectedItem().equals(moteur2)) {
		    				moteurV  = moteurDao.find(1);
		    			  }
	    			  if(moteurL.getSelectedItem().equals(moteur3)) {
		    				moteurV  = moteurDao.find(2);
		    			  }
	    			  if(moteurL.getSelectedItem().equals(moteur4)) {
		    				moteurV  = moteurDao.find(3);
		    			  }
	    			  if(moteurL.getSelectedItem().equals(moteur5)) {
		    				moteurV  = moteurDao.find(4);
		    			  }
	    			  if(moteurL.getSelectedItem().equals(moteur6)) {
		    				moteurV  = moteurDao.find(5);
		    			  }
	    			  if(moteurL.getSelectedItem().equals(moteur7)) {
		    				moteurV  = moteurDao.find(6);
		    			  }
	    			  if(moteurL.getSelectedItem().equals(moteur8)) {
		    				moteurV  = moteurDao.find(7);
		    			  }
	    			  if(moteurL.getSelectedItem().equals(moteur9)) {
		    				moteurV  = moteurDao.find(8);
		    			  }
	    			  
	    			  Statement state =  HsqldbConnection.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    				
	    			  
	    			  ResultSet nextID = HsqldbConnection.getInstance().prepareStatement("CALL NEXT VALUE FOR seq_vehicule_id").executeQuery();
	    				 if (nextID.next()) {
	    				 	 int ID = nextID.getInt(1);
	    				 	String query = "INSERT INTO vehicule(marque,moteur,prix,nom,id) VALUES("+marqueV+""+moteurL+""+prix+""+nom+")WHERE id"+ID;
	    				 	state.executeUpdate(query);
	    				}
	    				 
	    				
	    				
	    			
	    			
	    				
	    			}catch(SQLException erreur)	{
	    				erreur.printStackTrace();
	    			}
	    		
	      }

	      public String getOption(){
	        return (option1.isSelected()) ? option1.getText() : 
	               (option2.isSelected()) ? option2.getText() : 
	               (option3.isSelected()) ? option3.getText() : 
	               (option4.isSelected()) ? option4.getText() : 
	               (option5.isSelected()) ? option5.getText() :
	                option1.getText();  
	      }

	      public String getPrix(){
	        return (prix.getText().equals("")) ? "9999999" : prix.getText();
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
	content.setBackground(Color.getHSBColor(0.550f, 0.40f, 0.90f));
	
	this.getContentPane().add(content,BorderLayout.CENTER);
	this.setVisible(true);
	

}

}




