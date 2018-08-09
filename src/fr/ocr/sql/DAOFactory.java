package fr.ocr.sql;

import java.sql.Connection;

import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.option.Option;


public class DAOFactory {
protected static Connection conn = HsqldbConnection.getInstance();   
 
public static DAO<Vehicule> getClasseDAO(){
  return new VehiculeDAO(conn);
}

public static DAO<Option> getOptionDAO(){
  return new OptionDAO(conn);
}


public static DAO<Marque> getMarqueDAO() {
	return new MarqueDAO(conn);
}

public static DAO<Moteur> getMoteurDAO() {
	return new MoteurDAO(conn);
}

}
