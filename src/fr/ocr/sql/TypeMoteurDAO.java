package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;

public class TypeMoteurDAO extends DAO<TypeMoteur> {
	public TypeMoteurDAO(Connection conn) {
		super(conn);
	}

	public TypeMoteur create(TypeMoteur obj) {
		return obj;
	}

	public void delete(TypeMoteur obj) {
		try {
			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"DELETE FROM type_moteur WHERE id = " + obj.getId()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		return;
	}
	

	public boolean update(TypeMoteur obj) {
		return false;
	}

	public TypeMoteur find(int id) {
		TypeMoteur typeMoteur = new TypeMoteur();      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM type_moteur WHERE id = " + id);
			if(result.first())

				typeMoteur = new TypeMoteur(id,result.getString("description"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeMoteur;
	}



}