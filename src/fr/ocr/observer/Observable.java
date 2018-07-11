package fr.ocr.observer;

public interface Observable {

	public void addObservateur(Observateur obs);
	  public void update(Observateur obs);
	  public void delObservateur();
}
