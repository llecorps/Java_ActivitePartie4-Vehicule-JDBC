package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ocr.sql.DAO;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.VehiculeDAO;
import voiture.Vehicule;

//Notre listener pour le bouton
public class ButtonListener implements ActionListener {
	protected int column, row, id;
	protected JTable table;
	private static int idV ;
	private static final Logger logger = LogManager.getLogger();


	public void setColumn(int col) {
		this.column = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void actionPerformed(ActionEvent event) {


		
		idV = Integer.valueOf((String)table.getValueAt(row,this.column-2));
		DAO<Vehicule> vehiculeDao = new VehiculeDAO(HsqldbConnection.getInstance());
		Vehicule vehicule = vehiculeDao.find(idV) ;
		logger.info("Suppression du véhicule : "+vehicule.getNom());
			vehiculeDao.delete(vehicule);
			
			

	}

}