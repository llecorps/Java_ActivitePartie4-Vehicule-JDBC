package fr.ocr.sql;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.option.Option;

public class OptionDAO extends DAO<Option> {
	private static final Logger logger = LogManager.getLogger();
	public OptionDAO(Connection conn) {
		super(conn);
	}

	public Option create(Option obj) {
	return obj;
	}

	public void delete(Option obj) {
		try {
			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"DELETE FROM option WHERE id = " + obj.getId()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	return ;
	}
	

	public boolean update(Option obj) {
		return false;
	}

	public Option find(int id) {
		Option option = new Option();      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM option WHERE id = " + id);
			if(result.first())

				option = new Option(id,result.getString("description"), result.getDouble("prix"));
			    

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return option;
	}

	@Override
	public List<Option> findAll(List<Option> optionV) {
		List<Option> list = new ArrayList<Option>();
		Option option = new Option();
		try {
			
		
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM option ");
			if(result.first()) {
				option = new Option(result.getInt("id"),result.getString("description"),result.getDouble("prix"));
				list.add(option);
			}
			logger.info("Liste d'options : " + list);
		
	
		}catch(SQLException arg0) {
			arg0.printStackTrace();
		}
		return list;
	}



	}