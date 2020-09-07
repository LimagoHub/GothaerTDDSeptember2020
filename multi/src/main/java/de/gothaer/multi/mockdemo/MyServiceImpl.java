package de.gothaer.multi.mockdemo;

public class MyServiceImpl {
	
	private final MyDependency myDependency;

	public MyServiceImpl(final MyDependency myDependency) {
		this.myDependency = myDependency;
	}
	
	public void tueWas() {
		try {
			myDependency.foo("Hallo");
		} catch (Exception e) {
			throw new MyException(e);
		}
	}

	public int tueWasAnderes() {
		try {
			return myDependency.bar() + 10;
		} catch (Exception e) {
			throw new MyException(e);
		}
	}

	public int tueWasGanzAnderes() {
		return myDependency.fooBar("Welt") + 10;
	}

}
