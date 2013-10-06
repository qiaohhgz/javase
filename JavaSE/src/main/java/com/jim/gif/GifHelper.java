package com.jim.gif;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * <pre>
 *  注：
 * 
 * (1)AnimatedGifEncoder和GifDecoder，以及这	两个类涉及到的相关类，在网上搜索一下就可以找到。
 * 
 * (2)在linux系统下，如果你想支持更多系统外的字体，使用下面两句话，可以不用为系统添加字体，直接把字体文件拷贝到相应位置即可，但是需要jdk1.5环境。
 * File file = new File(fontPath);  //字体文件
 * Font font = Font.createFont(Font.TRUETYPE_FONT, file); //根据字体文件所在位置,创建新的字体对象
 * 如果是jdk1.5以下版本则需要为系统添加字体，因为createFont (int fontFormat, File  fontFile)
 * 这个方法，是从1.5才开始有的。
 * 
 * (3)g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
 * 我在测试中发现，当设置的字体过大的时候，会出现很明星的锯齿，后来在网上找到了这个解决方法。
 * 
 * (4)有了以上几个方法，就可以做出更好看的闪信了。我也是因为需求才写下这些方法的，美工做了一些热门词汇的gif图片 ，在短信转彩信遇到这些词汇时，要使用提供的图片 替换文字
 * </pre>
 * 
 * @author jim_qiao
 * 
 */
public class GifHelper {
	/**
	 * 把多张jpg图片 合成一张
	 * 
	 * @param pic
	 *            String[] 多个jpg文件名 包含路径
	 * @param newPic
	 *            String 生成的gif文件名 包含路径
	 */
	public synchronized void jpgToGif(String pic[], String newPic) {
		try {
			AnimatedGifEncoder e = new AnimatedGifEncoder(); // 网上可以找到此类
			e.setRepeat(0);
			e.start(newPic);
			BufferedImage src[] = new BufferedImage[pic.length];
			for (int i = 0; i < src.length; i++) {
				e.setDelay(200); // 设置播放的延迟时间
				src[i] = ImageIO.read(new File(pic[i])); // 读入需要播放的jpg文件
				e.addFrame(src[i]); // 添加到帧中
			}
			e.finish();
		} catch (Exception e) {
			System.out.println("jpgToGif Failed:");
			e.printStackTrace();
		}
	}

	/**
	 * 把gif图片 按帧拆分成jpg图片
	 * 
	 * @param gifName
	 *            String 小gif图片 (路径+名称)
	 * @param path
	 *            String 生成小jpg图片 的路径
	 * @return String[] 返回生成小jpg图片 的名称
	 */
	public synchronized String[] splitGif(String gifName, String path) {
		try {
			GifDecoder decoder = new GifDecoder();
			decoder.read(gifName);
			int n = decoder.getFrameCount(); // 得到frame的个数
			String[] subPic = new String[n];
			for (int i = 0; i < n; i++) {
				BufferedImage frame = decoder.getFrame(i); // 得到帧
				// int delay = decoder.getDelay(i); //得到延迟时间
				// 生成小的JPG文件
				subPic[i] = path + String.valueOf(i) + ".jpg";
				FileOutputStream out = new FileOutputStream(subPic[i]);
				ImageIO.write(frame, "jpeg", out);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(frame); // 存盘
				out.flush();
				out.close();
			}
			return subPic;
		} catch (Exception e) {
			System.out.println("splitGif Failed!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据提供的文字生成jpg图片
	 * 
	 * @param s
	 *            String 文字
	 * @param smallWidth
	 *            int 每个字的宽度和高度是一样的
	 * @param bgcolor
	 *            Color 背景色
	 * @param fontcolor
	 *            Color 字色
	 * @param fontPath
	 *            String 字体文件
	 * @param jpgname
	 *            String jpg图片 名
	 * @return
	 */
	public void createJpgByFont(String s, int smallWidth, Color bgcolor, Color fontcolor, String fontPath,
			String jpgname) {
		try { // 宽度 高度
			BufferedImage bimage = new BufferedImage(s.length() * smallWidth, smallWidth, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bimage.createGraphics();
			g.setColor(bgcolor); // 背景色
			g.fillRect(0, 0, smallWidth, smallWidth); // 画一个矩形
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 去除锯齿(当设置的字体过大的时候,会出现锯齿)
			g.setColor(fontcolor); // 字的颜色
			File file = new File(fontPath); // 字体文件
			Font font = Font.createFont(Font.TRUETYPE_FONT, file); // 根据字体文件所在位置,创建新的字体对象(此语句在jdk1.5下面才支持)
			g.setFont(font.deriveFont((float) smallWidth)); // font.deriveFont(float
															// f)复制当前 Font
															// 对象并应用新设置字体的大小
			g.drawString(s, 0, smallWidth); // 在指定坐标除添加文字
			g.dispose();
			FileOutputStream out = new FileOutputStream(jpgname); // 指定输出文件
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(50f, true);
			encoder.encode(bimage, param); // 存盘
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("createJpgByFont Failed!");
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public File[] getSystemFonts() {
		String sysFont = "C:/Windows/Fonts";
		return new File(sysFont).listFiles();
	}

	/**
	 * 将多个小图片 合成一张大jpg图 (小的jpg图片 按照行列顺序平铺)
	 * 
	 * @param smallJPG
	 *            ArrayList 一组小的jpg图片
	 * @param bigWidth
	 *            int 大图宽度
	 * @param smallWidth
	 *            int 单个文字生成的小图的宽度和高度是一致的
	 * @return
	 */
	public void createBigJPG(ArrayList smallJPG, int bigWidth, int smallWidth, Color bgColor, String picName) {
		try {
			if (bigWidth < smallWidth) // 如果大图片 的高度比小图片 的高度还小 直接返回
				return;
			int colCount = bigWidth / smallWidth; // 每行放置的字数
			int leftMargin = (int) ((bigWidth - colCount * smallWidth) / 2f); // 左边距
			int rowCount = smallJPG.size(); // 小图行数
			int setWidth = bigWidth; // 每列中间不留空隙，只留左右边距
			int setHeight = smallWidth * rowCount;
			// 按照大图片 宽高绘制一个背景图片
			BufferedImage bufImage = new BufferedImage(setWidth, setHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImage.createGraphics();
			g.setColor(bgColor); // 背景的颜色
			g.fillRect(0, 0, setWidth, setHeight);
			int y = 0; // 纵坐标
			for (int i = 0; i < rowCount; i++) { // 遍历每行
				ArrayList col = (ArrayList) (smallJPG.get(i));
				int x = leftMargin; // 横坐标 可能会出现左边距
				for (int j = 0; j < col.size(); j++) {
					String jpgname = (String) (col.get(j));
					ImageIcon icon = new ImageIcon(jpgname);
					Image img = icon.getImage();
					int imgWidth = img.getHeight(null);
					g.drawImage(img, x, y, null);
					x += imgWidth;
				}
				y += (smallWidth);
			}
			g.dispose();
			FileOutputStream out = new FileOutputStream(picName); // 指定输出文件
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); // 设置文件格式
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufImage); // 从图片
																					// 缓冲中读取
			param.setQuality(50f, true);
			encoder.encode(bufImage, param); // 存盘
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("createBigJPG Failed!");
			e.printStackTrace();
		}
	}
}
