package com.jim.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileDownload implements Runnable {
	private InputStream is;
	private OutputStream os;
	private MyListener listener;
	private boolean started = true;

	public FileDownload(InputStream is, OutputStream os) {
		this.is = is;
		this.os = os;
	}

	public void run() {
		if (is == null || os == null) {
			stop();
		}
		while (started) {
			try {
				byte[] bs = new byte[1024];
				int len = 0;
				while ((len = is.read(bs)) != -1) {
					os.write(bs, 0, len);
					if (len == bs.length) {
						bs = new byte[bs.length + 1024];
					}
					if (getListener() != null) {
						getListener().action(len);
					}
				}
				os.flush();
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		started = false;
	}

	public MyListener getListener() {
		return listener;
	}

	public void setListener(MyListener listener) {
		this.listener = listener;
	}

	public interface MyListener {
		public void action(int size);
	}
}
