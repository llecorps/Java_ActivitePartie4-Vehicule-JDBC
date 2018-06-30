package fr.ocr.ihm;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

	private String nom;

	public ButtonRenderer(String title) {
		nom = title;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean isFocus, int row, int col) {
		setText(nom);
		setBackground(Color.getHSBColor(0.536f, 0.43f, 0.86f));
		setBorder(BorderFactory.createLineBorder(Color.black));
		// On retourne notre bouton
		return this;
	}
}