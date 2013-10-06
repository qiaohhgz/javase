package com.jim.audio.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class AePlayWave extends Thread {

	private String filename;
	private InputStream is;

	public AePlayWave(String wavfile) {
		filename = wavfile;
	}

	public AePlayWave(InputStream is) {
		this.is = is;
	}

	public void run() {

		AudioInputStream audioInputStream = null;
		try {
			if (filename != null) {
				File soundFile = new File(filename);
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			} else {
				audioInputStream = AudioSystem.getAudioInputStream(is);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}
	}
}
