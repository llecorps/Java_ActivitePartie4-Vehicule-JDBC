package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import fr.ocr.sql.DAO;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.VehiculeDAO;
import voiture.Vehicule;

public class ViewDetailVehiculeListener extends ButtonListener {
	private static Integer id;
	private static int idV ;
	

	public void actionPerformed(ActionEvent e) {
		
		ZViewVehicule zView = new ZViewVehicule(null,"Détails du vehicule",true);	
		
		idV = e.getID();
	}

	public static int getID() {
		return idV;
		
	}

}
