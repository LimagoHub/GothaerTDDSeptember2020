package de.gothaer.multi;

public class MultiImpl implements Multi {
	
	@Override
	public long mult(int a, int b) {
		long retval = 0;
		
		for (int i = 0; i < a; i++) {
			retval += b;
		}
		return retval;
	}

}
