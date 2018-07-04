package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.option.Option;

public class VehiculeDAO extends DAO<Vehicule> {

	private static final Logger logger = LogManager.getLogger();

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
			if(result.next()) {

				vehicule = new Vehicule();
				MarqueDAO marqueDao = new MarqueDAO(this.connect);
				vehicule.setMarque(marqueDao.find(result.getInt("id")));

				MoteurDAO moteurDao = new MoteurDAO(this.connect);
				vehicule.setMarque(marqueDao.find(result.getInt("id")));



				OptionDAO optionDao = new OptionDAO(this.connect);
				result = this.connect.createStatement().executeQuery(
						"SELECT * FROM vehicule_option WHERE id_vehicule = "+ id );

				for(int i = 1 ; i < result.getRow() ; i++) {

					logger.info("Je passe dans la boucle");
					vehicule.addOption(optionDao.find(i));

				}




			}  

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicule;
	}
}

