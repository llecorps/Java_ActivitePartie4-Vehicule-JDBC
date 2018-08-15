package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ocr.observer.Observateur;
import fr.yoannroche.ihm.ZAddVehicule;
import voiture.Vehicule;

public class NewVehiculeListener implements ActionListener {
	private Observateur observateur;
	
	public NewVehiculeListener(Observateur obs) {
		this.observateur = obs;
	}

	public void actionPerformed(ActionEvent e) {
		Vehicule vehicule = new Vehicule();
		new ZAddVehicule(null, "Ajout d'un vehicule", true, vehicule, observateur);
	}

}
