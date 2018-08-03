package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import fr.ocr.observer.Observable;
import fr.ocr.observer.Observateur;
import fr.yoannroche.ihm.ZAddVehicule;
import voiture.Vehicule;
public class NewVehiculeListener implements ActionListener,Observable {

	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	 

	public NewVehiculeListener(JFrame f) {
		
	}

	public void actionPerformed(ActionEvent e) {
		
			Vehicule vehicule = new Vehicule();
		new ZAddVehicule(null,"Ajout d'un vehicule",true,vehicule,this);
	    
	}

	public void addObservateur(Observateur obs) {
		this.listObservateur.add(obs);
		
	}

	@Override
	public void delObservateur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateObservateur() {
		for(Observateur obs : this.listObservateur)
			obs.update();
		
		
	}

		
}
