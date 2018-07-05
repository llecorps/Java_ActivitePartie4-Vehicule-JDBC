package fr.ocr.sql;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.Marque;
import voiture.moteur.Moteur;

public class MarqueDAO extends DAO<Marque> {
	public MarqueDAO(Connection conn) {
		super(conn);
	}

	public Marque create(Marque obj) {
		try {
		ResultSet result = this.connect
				.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
								"SELECT * FROM marque"
								);
		if(result.first()){
			PreparedStatement prepare = this.connect
					.prepareStatement(
							"INSERT INTO marque (id, nom)"+
									"VALUES(?, ?)"
							);
			prepare.setLong(1, obj.getId());
			prepare.setString(2, obj.getNom());
			
		}
	} catch (SQLException e) {
        e.printStackTrace();
}
return obj;
}

		public boolean delete(Marque obj) {
			return false;
		}

		public boolean update(Marque obj) {
			return false;
		}

		public Marque find(int id) {
			Marque marque = new Marque();      

			try {
				ResultSet result = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM marque WHERE id =" + id);
				if(result.first())

					marque = new Marque(id,result.getString("nom"));

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return marque;
		}


	}