package de.gothaer.bestelldemo.services;

import de.gothaer.bestelldemo.persistence.BestellRepository;
import de.gothaer.bestelldemo.persistence.model.Bestellung;

public class BestellServiceImpl {
	
	private final BestellRepository bestellRepository;
	private final BonitätsPrüfung bonitätsPrüfung;
	
	
	
	public BestellServiceImpl(final BestellRepository bestellRepository, final BonitätsPrüfung bonitätsPrüfung) {
		super();
		this.bestellRepository = bestellRepository;
		this.bonitätsPrüfung = bonitätsPrüfung;
	}



	/**
	
	 * 
	 * 
	 * @param bestellung darf nicht null sein
	 * @param creditcard nicht null, genau 11 Stellen, beginnt mit M oder V, endet mit 10 ziffern, 
	 * @param amount darf nicht negativ
	 * @throws BestellServiceException bei allen, auch technischen Problemen
	 * @throws KundeIstPleiteException Kunde ist nicht solvent.
	 */
	public void bestellen(Bestellung bestellung, String creditcard /* M oder V gefolgt von genau 10 zieffern */, double amount) throws BestellServiceException, KundeIstPleiteException{
		
	}

}
