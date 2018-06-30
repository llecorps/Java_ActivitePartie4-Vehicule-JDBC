package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.Vehicule;
import voiture.moteur.Moteur;

public class VehiculeDAO extends DAO<Vehicule> {

	public VehiculeDAO(Connection conn) {
		super(conn);
	}

	public boolean create(Vehicule obj) {
		return false;
	}

	public boolean delete(Vehicule obj) {
		return false;
	}

	public boolean update(Vehicule obj) {
		return false;
	}

	public Vehicule find(int id) {
		Vehicule vehicule = new Vehicule();    

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM vehicule WHERE id = " + id);
			if(result.first())

				vehicule = new Vehicule();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicule;
	}
}

