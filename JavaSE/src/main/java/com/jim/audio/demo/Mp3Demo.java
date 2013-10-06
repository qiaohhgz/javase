package com.jim.audio.demo;

import javax.media.Manager;
import javax.media.Player;
import javax.media.Time;

import org.apache.log4j.Logger;

import com.jim.util.MyHelper;

public class Mp3Demo {
	static final Logger log = Logger.getLogger(Mp3Demo.class);

	public static void main(String[] args) {
		try {
			Player player = Manager.createRealizedPlayer(MyHelper.getResource("demo.wav"));
			System.out.println("获得文件播放时间: " + player.getDuration().getSeconds());
			player.prefetch();
			player.setMediaTime(new Time(10.0));// 从莫个时间段开始播放
			player.start();
		} catch (Exception ex) {
			log.error(ex);
		}
	}
}