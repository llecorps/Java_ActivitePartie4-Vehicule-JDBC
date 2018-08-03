package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;

import fr.ocr.sql.DAO;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.VehiculeDAO;
import fr.yoannroche.ihm.ZViewVehicule;
import voiture.Vehicule;

public class ViewDetailVehiculeListener extends ButtonListener {
	private static int idV ;
	
	

	public void actionPerformed(ActionEvent e) {
		
		idV = Integer.valueOf((String)table.getValueAt(row,this.column-1));
		DAO<Vehicule> vehiculeDao = new VehiculeDAO(HsqldbConnection.getInstance());
	    Vehicule vehicule = vehiculeDao.find(idV);
	    new ZViewVehicule(null,"Détails du vehicule",true,vehicule);	
	}

	public static int getID() {
		return idV;
		
	}

}
