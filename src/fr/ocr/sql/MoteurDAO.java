package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.moteur.Moteur;
import voiture.option.Option;

public class MoteurDAO extends DAO<Moteur> {
	
	
	public MoteurDAO(Connection conn) {
		super(conn);
	}

	public Moteur create(Moteur obj) {
		return obj;
	}

	public void delete(Moteur obj) {
		try {
			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"DELETE FROM moteur WHERE id = " + obj.getId()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		return ;
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
						"SELECT moteur,id FROM moteur WHERE id = " + id );
				while(result.next())
				moteur.setType(typeMoteurDao.find(result.getInt("moteur")));
		     




		} catch (SQLException e) {
			e.printStackTrace();
		}
		return moteur;
	}


	@Override
	public java.util.List<Option> findAll(java.util.List<Option> optionV) {
		// TODO Auto-generated method stub
		return null;
	}



}


