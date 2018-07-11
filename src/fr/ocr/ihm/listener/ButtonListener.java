package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ocr.ihm.Garage;
import fr.ocr.observer.Observable;
import fr.ocr.observer.Observateur;
import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOTableFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.VehiculeDAO;
import voiture.Vehicule;

//Notre listener pour le bouton
public class ButtonListener implements ActionListener,Observable {
	protected int column, row, id;
	protected JTable table;
	private Observateur obs;
	DatabaseTable tableau;
	private static int idV ;
	private static final Logger logger = LogManager.getLogger();
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	


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
			update(obs);
		
			
			 
			
			
			
			
			
			

	}

	@Override
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observateur obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delObservateur() {
		// TODO Auto-generated method stub
		
	}

	 
		}

