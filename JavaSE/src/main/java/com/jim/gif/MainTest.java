package com.jim.gif;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

public class MainTest {

	@Test
	public void testAnimatedGifEncoder() {
		try {
			AnimatedGifEncoder e = new AnimatedGifEncoder();
			e.start(new FileOutputStream(new File("hecheng.gif")));
			e.setDelay(1000);
			e.addFrame(ImageIO.read(new File("C:/Users/Public/Pictures/Sample Pictures/Chrysanthemum.jpg")));
			e.addFrame(ImageIO.read(new File("C:/Users/Public/Pictures/Sample Pictures/Desert.jpg")));
			e.addFrame(ImageIO.read(new File("C:/Users/Public/Pictures/Sample Pictures/Hydrangeas.jpg")));
			e.addFrame(ImageIO.read(new File("C:/Users/Public/Pictures/Sample Pictures/Penguins.jpg")));
			e.finish();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void testCreateJpgByFont() {
		GifHelper h = new GifHelper();
		File[] fs = h.getSystemFonts();
		for (File file : fs) {
			try {
				h.createJpgByFont("A", 50, Color.gray, Color.yellow, file.getAbsolutePath(), "files/" + file.getName()
						+ ".jpg");
			} catch (Exception e) {
				continue;
			}
		}
	}
}
