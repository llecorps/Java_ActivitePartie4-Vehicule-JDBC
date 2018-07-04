package fr.ocr.sql;

import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;

public class MoteurDAO extends DAO<Moteur> {
	
	
	public MoteurDAO(Connection conn) {
		super(conn);
	}

	public boolean create(Moteur obj) {
		return false;
	}

	public boolean delete(Moteur obj) {
		return false;
	}

	public boolean update(Moteur obj) {
		return false;
	}

	public Moteur find(int id) {
		Moteur moteur = new Moteur();      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM moteur WHERE id = " + id);
			
			if(result.first())


				moteur = new Moteur(id,null, result.getString("cylindre"), result.getDouble("prix"));
			    TypeMoteurDAO typeMoteurDao = new TypeMoteurDAO(this.connect);
				result = this.connect.createStatement().executeQuery(
						"SELECT id,description FROM type_moteur");
				while(result.next())
				moteur.setType(typeMoteurDao.find(result.getInt("id")));
		     




		} catch (SQLException e) {
			e.printStackTrace();
		}
		return moteur;
	}



}


