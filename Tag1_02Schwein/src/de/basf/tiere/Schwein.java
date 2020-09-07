package de.basf.tiere;

public class Schwein {
	// Instanzvariablen
	private String name;
	private int gewicht;
	

	
	// Instanz-Konstruktor
	public Schwein() { /* Konstruktor a ruft Konstruktor B */
		this("Herbert");// Konstruktorweiterleitung, muss der erste Befehl in einem Konstruktor sein
	}
	
	// Instanz-Konstruktor
	public Schwein(String name) { // Das ist Konstruktor B
		this.setName(name);
		this.setGewicht(10);
		
	}
	
	

	public String getName() {
		return this.name;
	}

	public final void setName(String name /* "Miss piggy" */) {
		if(name == null || "elsa".equalsIgnoreCase(name))
			throw new IllegalArgumentException("Falscher Name");
		this.name = name;
	}

	public int getGewicht() {
		return this.gewicht;
	}

	private void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}


	public void fressen() {
		setGewicht(getGewicht() + 1); 
	}

	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Schwein [name=");
		builder.append(name);
		builder.append(", gewicht=");
		builder.append(gewicht);
		builder.append("]");
		return builder.toString();
	}

	
}
