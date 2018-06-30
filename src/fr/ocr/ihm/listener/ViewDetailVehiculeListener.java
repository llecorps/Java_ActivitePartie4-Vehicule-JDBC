package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;

import fr.ocr.sql.DAO;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.VehiculeDAO;
import voiture.Vehicule;

public class ViewDetailVehiculeListener extends ButtonListener {
	private Integer id;

	public void actionPerformed(ActionEvent e) {
		
		ZViewVehicule zView = new ZViewVehicule(null,"Détails du vehicule",true);	
	}

}
