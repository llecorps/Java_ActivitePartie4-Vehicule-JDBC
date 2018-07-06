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
				
				
				
				
				PreparedStatement prepar = this.connect
						.prepareStatement("INSERT INTO vehicule (id,nom,prix,marque,moteur)"+"VALUES(?,?,?,?,?)");
				prepar.setLong(1, ID);
				prepar.setString(2, obj.getNom());
				prepar.setDouble(3, obj.getPrix());
				prepar.setInt(4, obj.getMarque().getId());
				prepar.setInt(5, obj.getMoteur().getId());
				prepar.executeUpdate();
				
				logger.info("Ajout de l'id, nom, prix, marque, moteur");
				
				
				

				PreparedStatement prepare = this.connect
						.prepareStatement(
								"INSERT INTO vehicule_option (id_vehicule,id_option)"+
										"VALUES(?,?)"
								);
				prepare.setInt(1, ID);
				prepare.setInt(2, obj.getId());
				prepare.executeUpdate();
				
				logger.info("Ajout de l'option vehicule");
			}
	
			 

			 

		}catch(SQLException arg0) {
			arg0.printStackTrace();

		}
		return obj;

	}

	public void delete(Vehicule obj) {
		try {

			this.connect	
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
							"DELETE FROM vehicule_option WHERE id_vehicule = " + obj.getId() 
							);
			this.connect	
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
							"DELETE FROM marque WHERE id = " + obj.getId() 
							);
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
		return;
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

