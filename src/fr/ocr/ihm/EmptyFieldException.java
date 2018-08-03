package fr.ocr.ihm;

import javax.swing.JOptionPane;

public class EmptyFieldException extends Exception {
	public EmptyFieldException(String err) {
		super(err);
		JOptionPane.showMessageDialog(null, err, "Champs obligatoire manquant",
				JOptionPane.ERROR_MESSAGE);
	}
}
