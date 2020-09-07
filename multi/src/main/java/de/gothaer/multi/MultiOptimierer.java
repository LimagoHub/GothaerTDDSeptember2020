package de.gothaer.multi;

public class MultiOptimierer implements Multi{
	
	private final Multi multi;

	public MultiOptimierer(Multi multi) {
		this.multi = multi;
	}

	public long mult(int a, int b) {
		if(a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		return multi.mult(a, b);
	}
	
	

}
