package com.jim.subject;

public class KeyAltDemo {
	public static void main(String[] args) {
		boolean[] bs = { false, false, false };
		for (int i = 0; i < 100000; i++) {
			char t = (char) i;
			if (bs[0] && bs[1] && bs[2]) {
				break;
			}
			if ('我' == t) {
				System.out.println("i=" + i + " " + (char) i);
				bs[0] = true;
			} else if ('是' == t) {
				System.out.println("i=" + i + " " + (char) i);
				bs[1] = true;
			} else if ('猪' == t) {
				System.out.println("i=" + i + " " + (char) i);
				bs[2] = true;
			}
		}
	}
}
