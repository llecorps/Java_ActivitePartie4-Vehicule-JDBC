package fr.ocr.ihm;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import fr.ocr.ihm.listener.ButtonListener;
/**
 * Classe permettant de d�finir les comportements
 * des boutons du JTable 
 * @author cysboy
 */
public class ButtonEditor extends DefaultCellEditor {

	protected JButton button;
	private boolean isPushed;
	private ButtonListener bListener;
	private String title = "";

	// Constructeur avec une CheckBox
	public ButtonEditor(JCheckBox checkBox, String t) {
		// Par d�faut, ce type d'objet travaille avec un JCheckBox
		super(checkBox);
		// On cr�e � nouveau un bouton
		button = new JButton();
		button.setOpaque(true);
		// On lui attribue un listener
		bListener = new ButtonListener();
		button.addActionListener(bListener);
		title = t;
	}

	public ButtonEditor(JCheckBox checkBox, String t, ButtonListener l) {
		// Par d�faut, ce type d'objet travaille avec un JCheckBox
		super(checkBox);
		// On cr�e � nouveau un bouton
		button = new JButton();
		button.setOpaque(true);
		// On lui attribue un listener
		bListener = l;
		button.addActionListener(bListener);
		title = t;
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// On pr�cise le num�ro de ligne � notre listener
		bListener.setRow(row);
		// Idem pour le num�ro de colonne
		bListener.setColumn(column);
		// On passe aussi le tableau en param�tre pour des actions potentielles
		bListener.setTable(table);

		// On r�affecte le libell� au bouton
		button.setText(title);
		// On renvoie le bouton
		return button;
	}

}