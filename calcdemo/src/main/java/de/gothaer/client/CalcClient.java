package de.gothaer.client;

import de.gothaer.math.Calculator;

public class CalcClient {

	private static final int TOTAL_KOPLIZIERTE_FORMELNR1 = 3;
	private static final int TOTAL_KOPLIZIERTE_FORMELNR2 = 4;
	
	private final Calculator calculator;

	public CalcClient(Calculator calculator) {
		this.calculator = calculator;
	}

	public void go() {
		System.out.println(calculator.add(3,4));
		System.out.println(calculator.add(3,5));
	}

}
