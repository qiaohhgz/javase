package com.jim.audio.demo;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioSystem;

public class AudioDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Type[] types = AudioSystem.getAudioFileTypes();
		for (Type type : types) {
			System.out.println(type);
		}
		AePlayWave apw = new AePlayWave("F:/IDEMyEclipse10/Workspaces/JavaSE/src/com/jim/audio/demo/Restaurant Demo Call.mp3");
		apw.start();
	}
}
