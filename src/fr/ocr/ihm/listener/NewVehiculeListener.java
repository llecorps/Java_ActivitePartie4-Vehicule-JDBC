package fr.ocr.ihm.listener;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.ocr.sql.DAO;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.VehiculeDAO;
import voiture.Vehicule;
public class NewVehiculeListener implements ActionListener {

	private JFrame frame;
	private static final Logger logger = LogManager.getLogger();

	public NewVehiculeListener(JFrame f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		try {
		Connection connect = HsqldbConnection.getInstance();
		connect.setAutoCommit(false);
		ZAddVehicule zAdd = new ZAddVehicule(null,"Ajout d'un vehicule",true);
		
	    
		 
		}catch(SQLException arg0) {
			logger.error(arg0);
		}
			 
	}	
}
