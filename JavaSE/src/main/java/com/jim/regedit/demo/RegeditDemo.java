package com.jim.regedit.demo;

import java.util.prefs.Preferences;

public class RegeditDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Preferences prefs = Preferences.userRoot();
		String[] keys = prefs.childrenNames();
		for (int i = 0; i < keys.length; i++) {
			System.out.println("key:" + keys[i]);
			System.out.println(prefs.get(keys[i], "null"));
		}
		System.out.println(prefs.absolutePath());
	}
}
