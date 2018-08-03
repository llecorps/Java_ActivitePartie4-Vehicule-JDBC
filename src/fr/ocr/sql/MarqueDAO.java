package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.Marque;
import voiture.option.Option;

public class MarqueDAO extends DAO<Marque> {
	public MarqueDAO(Connection conn) {
		super(conn);
	}

	public Marque create(Marque obj) {
		return obj;
	}

		public void delete(Marque obj) {
			try {
				
				this.connect	
	                .createStatement(
	                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                	ResultSet.CONCUR_UPDATABLE
	                 ).executeUpdate(
	                	"DELETE FROM marque WHERE id = " + obj.getId()
	                 );

		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
			return;
		
	
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

		

		@Override
		public java.util.List<Option> findAll(java.util.List<Option> optionV) {
			// TODO Auto-generated method stub
			return null;
		}


	}