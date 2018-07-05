package fr.ocr.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;
import voiture.option.Option;

public class VehiculeDAO extends DAO<Vehicule> {

	private static final Logger logger = LogManager.getLogger();

	public VehiculeDAO(Connection conn) {
		super(conn);
	}

	public Vehicule create(Vehicule obj) {

try {
		
	connect.setAutoCommit(false);
	ResultSet nextID = connect.prepareStatement("CALL NEXT VALUE FOR seq_vehicule_id").executeQuery();
	 if (nextID.next()) {
	 	 int ID = nextID.getInt(1);
	 	PreparedStatement prepare = this.connect
                .prepareStatement(
        			"INSERT INTO vehicule (marque,moteur,prix,nom,id)"+
        			"VALUES(?,?,?,?,?)"
        		);
	 	prepare.setString(1, obj.getMarque().getNom());
		prepare.setString(2, obj.getMoteur().getCylindre());
		prepare.setDouble(3, obj.getPrix());
		prepare.setString(4, obj.getNom());
		prepare.setInt(5, ID);
		
		prepare.executeUpdate();
	}
		

			
}catch(SQLException arg0) {
	arg0.printStackTrace();
	
}
return obj;
			
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
			
			if(result.next())
				

				vehicule = new Vehicule(id,result.getString("nom"), null, null,result.getDouble("prix"));
			    
				
				
				MarqueDAO marqueDao = new MarqueDAO(connect);
				result = this.connect.createStatement().executeQuery(
						"SELECT marque,id FROM vehicule WHERE id = " + id );
				while(result.next())
				vehicule.setMarque(marqueDao.find(result.getInt("marque")));

				
				MoteurDAO moteurDao = new MoteurDAO(this.connect);
				result = this.connect.createStatement().executeQuery(
						"SELECT moteur,id FROM vehicule WHERE id = " + id);
				while(result.next()) 
				vehicule.setMoteur(moteurDao.find(result.getInt("moteur")));
						
			
				OptionDAO optionDao = new OptionDAO(this.connect);
				result = this.connect.createStatement().executeQuery(
						"SELECT id_option FROM vehicule_option WHERE id_vehicule = "+ id );
				while(result.next()) {

					logger.info("Recup d'une option");
					vehicule.addOption(optionDao.find(result.getInt("id_option")));
				}
				
				




			

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
		}
		return vehicule;
	}
}

