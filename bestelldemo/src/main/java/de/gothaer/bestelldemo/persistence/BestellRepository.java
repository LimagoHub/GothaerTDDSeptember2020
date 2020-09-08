package de.gothaer.bestelldemo.persistence;

import de.gothaer.bestelldemo.persistence.model.Bestellung;

public interface BestellRepository {
	
	boolean saveOrUpdate(Bestellung bestellung); // RTE

}
