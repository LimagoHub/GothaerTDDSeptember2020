package de.gothaer.bestelldemo.services;

import java.rmi.RemoteException;

public interface BonitätsPrüfung {
	
	boolean process(String cardtype /* MASTER oder VISA */, String cardnumber /* genau 10 Ziffern */, double amount) throws RemoteException;

}
