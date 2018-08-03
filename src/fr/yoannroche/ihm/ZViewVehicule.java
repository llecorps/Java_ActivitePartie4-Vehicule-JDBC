package fr.yoannroche.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import voiture.Vehicule;
import voiture.option.Option;

public class ZViewVehicule extends JDialog{
	
	private Vehicule vehicule;

	public ZViewVehicule(JFrame parent, String title, boolean modal, Vehicule vehicule) {
		
		super(parent,title,modal);
		this.vehicule = vehicule;
		this.setSize(new Dimension(650,330));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		this.initComponent();
	}
	private void initComponent() {
		
		try {
		 
			 
		JPanel panView = new JPanel();
		panView.setBackground(Color.getHSBColor(0.550f, 0.40f, 0.90f));
		panView.setPreferredSize(new Dimension(200,55));
		panView.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel nom = new JPanel();
		nom.setBackground(Color.white);
		nom.setPreferredSize(new Dimension(270,55));
		nom.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel nomVehicule = new JLabel();
		nomVehicule.setText("   "+vehicule.getNom());
		nomVehicule.setBorder(BorderFactory.createTitledBorder(" Nom du Vehicule "));
		nomVehicule.setPreferredSize(new Dimension(240,40));
        nomVehicule.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel marque = new JPanel();
		marque.setBackground(Color.white);
		marque.setPreferredSize(new Dimension(270,55));
		marque.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel marqueVehicule = new JLabel();
		marqueVehicule.setText(vehicule.getMarque().getNom());
		marqueVehicule.setBorder(BorderFactory.createTitledBorder(" Marque du Vehicule "));
		marqueVehicule.setPreferredSize(new Dimension(240,40));
        marqueVehicule.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel moteur = new JPanel();
		moteur.setBackground(Color.white);
		moteur.setPreferredSize(new Dimension(270,55));
		moteur.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel moteurVehicule = new JLabel();
		moteurVehicule.setText(vehicule.getMoteur().getCylindre()+"  "+vehicule.getMoteur().getType().getNom());
		moteurVehicule.setBorder(BorderFactory.createTitledBorder(" Type de moteur du vehicule "));
		moteurVehicule.setPreferredSize(new Dimension(240,40));
        moteurVehicule.setHorizontalAlignment(JLabel.CENTER);
       
        JPanel prix = new JPanel();
		prix.setBackground(Color.white);
		prix.setPreferredSize(new Dimension(270,55));
		prix.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel prixVehicule = new JLabel();
		prixVehicule.setText(Double.toString(vehicule.getPrix()));
		prixVehicule.setBorder(BorderFactory.createTitledBorder(" Prix du vehicule "));
		prixVehicule.setPreferredSize(new Dimension(240,40));
        prixVehicule.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel option = new JPanel();
		option.setBackground(Color.white);
		option.setPreferredSize(new Dimension(500,85));
		option.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel optionVehicule = new JLabel();
		List<Option> optionL = vehicule.getOptions();	
		optionVehicule.setText(optionL.toString());
		
		optionVehicule.setBorder(BorderFactory.createTitledBorder(" Options du vehicule "));
		optionVehicule.setPreferredSize(new Dimension(450,70));
        optionVehicule.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel total = new JPanel();
		total.setBackground(Color.getHSBColor(0.367f, 0.24f, 0.90f));
		total.setPreferredSize(new Dimension(350,65));
		total.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel totalVehicule = new JLabel();
		totalVehicule.setText(Double.toString(vehicule.getPrixTotal()));
		totalVehicule.setBorder(BorderFactory.createTitledBorder(" Prix total du vehicule "));
		totalVehicule.setPreferredSize(new Dimension(300,50));
        totalVehicule.setHorizontalAlignment(JLabel.CENTER);
        	
        prix.add(prixVehicule);
		nom.add(nomVehicule);
		marque.add(marqueVehicule);
		moteur.add(moteurVehicule);
		option.add(optionVehicule);
		total.add(totalVehicule);
		getContentPane().add(panView);
		panView.add(nom);
		JPanel espace1 = new JPanel();
		espace1.setPreferredSize(new Dimension(40,15));
		espace1.setBackground(Color.getHSBColor(0.550f, 0.30f, 0.80f));
		espace1.setBorder(BorderFactory.createLineBorder(Color.black));
		panView.add(espace1);
		panView.add(marque);
		panView.add(moteur);
		JPanel espace2 = new JPanel();
		espace2.setPreferredSize(new Dimension(40,15));
		espace2.setBackground(Color.getHSBColor(0.550f, 0.30f, 0.80f));
		espace2.setBorder(BorderFactory.createLineBorder(Color.black));
	    panView.add(espace2);
		panView.add(prix);
		panView.add(option);	
		panView.add(total);
		this.setVisible(true);
		
		
		} catch (Exception e) {
		      e.printStackTrace();
		    }  
}
	
}
