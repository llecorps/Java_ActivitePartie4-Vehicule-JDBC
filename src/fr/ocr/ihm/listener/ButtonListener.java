package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;

import fr.ocr.sql.HsqldbConnection;

//Notre listener pour le bouton
public class ButtonListener implements ActionListener {
	protected int column, row, id;
	protected JTable table;


	public void setColumn(int col) {
		this.column = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void actionPerformed(ActionEvent event) {
		try {
		String query = ("DELETE marque FROM vehicule WHERE row +"+this.row);
		query += "DELETE moteur FROM vehicule WHERE row + "+this.row ;
		query += "DELETE prix FROM vehicule WHERE row + "+this.row ;
		query += "DELETE nom FROM vehicule WHERE row + "+this.row ;
		Statement state = HsqldbConnection.getInstance().createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		 state.executeUpdate(query);
		state.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}