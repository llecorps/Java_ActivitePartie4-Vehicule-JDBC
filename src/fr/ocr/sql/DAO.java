package fr.ocr.sql;

import java.sql.Connection;
import java.util.List;

import fr.ocr.sql.HsqldbConnection;
import voiture.Vehicule;
import voiture.option.Option;

public abstract class DAO<T> {
  protected Connection connect = null;
   
  public DAO(Connection conn){
    this.connect = conn;
  }
   
  /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
  public abstract T create(T obj);

  /**
  * Méthode pour effacer
  * @param obj 
 * @return 
  */
  public abstract void delete(T obj);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
  */
  public abstract T find(int id);

public abstract List<Option> findAll(List<Option> optionV);
}