package de.gothaer.bestelldemo.services;

import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		try {
			final Pattern pattern = Pattern.compile("^(M|V)(\\d{10})$");
			
			if(bestellung == null)
				throw new BestellServiceException("Bestellung darf nicht null sein.");
			if(amount < 0)
				throw new BestellServiceException("Amount darf nicht negativ sein.");
			if(creditcard == null)
				throw new BestellServiceException("Kreditkarte darf nicht null sein.");
			
			Matcher matcher = pattern.matcher(creditcard);
			if(! matcher.matches())
				throw new BestellServiceException("Kreditkartennummer hat falsches Format");
			String cardtype = matcher.group(1).equals("M")? "MASTER":"VISA";
			String cardnumber = matcher.group(2);
			
			if(! bonitätsPrüfung.process(cardtype, cardnumber, amount)) {
				throw new KundeIstPleiteException("Geh heim!");
			} 
			
			bestellRepository.saveOrUpdate(bestellung);
		} catch (RemoteException | RuntimeException e) {
			throw new BestellServiceException("Fehler im Service",e);
		} 
	}

}
