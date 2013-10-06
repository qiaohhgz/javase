package com.jim.subject;

public class FinalizeDemo {

	public FinalizeDemo() {
		System.out.println("begin");
	}

	void init() {
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize");
	}

	public static void main(String[] args) {
		new FinalizeDemo().init();
	}
}