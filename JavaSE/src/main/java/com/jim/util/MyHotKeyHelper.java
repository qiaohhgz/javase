package com.jim.util;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

/**
 * <pre>
 * F7 stop
 * F8 start
 * </pre>
 * 
 * @author jim_qiao
 * 
 */
public class MyHotKeyHelper implements HotkeyListener {
	public static final Logger log = Logger.getLogger(MyHotKeyHelper.class);
	private static final Map<Integer, MyKeyEventBean> KEY_MAP = new HashMap<Integer, MyKeyEventBean>();
	private static final Map<Integer, Runnable> KEY_MAP_REG = new HashMap<Integer, Runnable>();
	private int activateKeyCode = KeyEvent.VK_F7;
	private int sleepKeyCode = KeyEvent.VK_F8;
	private int destroyKeyCode = KeyEvent.VK_F9;

	public MyHotKeyHelper() {
		initHotkey();
	}

	public MyHotKeyHelper(int activateKeyCode, int sleepKeyCode, int destroyKeyCode) {
		this.activateKeyCode = activateKeyCode;
		this.sleepKeyCode = sleepKeyCode;
		this.destroyKeyCode = destroyKeyCode;
		initHotkey();
	}

	private void initHotkey() {
		// int modifier = JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT;
		JIntellitype.getInstance().registerHotKey(activateKeyCode, 0, activateKeyCode);
		JIntellitype.getInstance().registerHotKey(sleepKeyCode, 0, sleepKeyCode);
		JIntellitype.getInstance().registerHotKey(destroyKeyCode, 0, destroyKeyCode);
		log.info("activate:" + KeyEvent.getKeyText(activateKeyCode));
		log.info("sleep:" + KeyEvent.getKeyText(sleepKeyCode));
		log.info("destroy:" + KeyEvent.getKeyText(destroyKeyCode));
		JIntellitype.getInstance().addHotKeyListener(this);
	}

	public void onHotKey(int keyCode) {
		if (keyCode == activateKeyCode) {
			activate();
		} else if (keyCode == sleepKeyCode) {
			sleep();
		} else if (keyCode == destroyKeyCode) {
			destroy();
		} else {
			Set<Integer> keySet = KEY_MAP_REG.keySet();
			for (Integer key : keySet) {
				if (key.intValue() == keyCode) {
					log.debug("keyCode-" + keyCode + ":start");
					new Thread(KEY_MAP_REG.get(key)).start();
				}
			}
		}
	}

	public void destroy() {
		log.info("destroy");
		Set<Integer> keySet = KEY_MAP_REG.keySet();
		for (Integer key : keySet) {
			JIntellitype.getInstance().unregisterHotKey(key);
		}
		keySet = KEY_MAP.keySet();
		for (Integer key : keySet) {
			JIntellitype.getInstance().unregisterHotKey(key);
		}
		KEY_MAP.clear();
		KEY_MAP_REG.clear();
		JIntellitype.getInstance().cleanUp();
		log.info("cleanUp success");
	}

	public void activate() {
		KEY_MAP_REG.clear();
		log.debug("activate");
		Set<Integer> keySet = KEY_MAP.keySet();
		for (Integer key : keySet) {
			MyKeyEventBean bean = KEY_MAP.get(key);
			registerHotKey(bean.getKeyCode(), bean.isShiftDown(), bean.isControlDown(), bean.isAltDown(), bean.getRunnable());
		}
	}

	public void sleep() {
		log.debug("sleep");
		Set<Integer> keySet = KEY_MAP_REG.keySet();
		for (Integer key : keySet) {
			JIntellitype.getInstance().unregisterHotKey(key);
		}
	}

	public void add(KeyEvent key, Runnable runnable) {
		add(key.getKeyCode(), key.isShiftDown(), key.isControlDown(), key.isAltDown(), runnable);
	}

	public void add(int keyCode, boolean isShiftDown, boolean isControlDown, boolean isAltDown, Runnable runnable) {
		KEY_MAP.put(keyCode, new MyKeyEventBean(keyCode, isShiftDown, isControlDown, isAltDown, runnable));
		activate();
	}

	public void add(int keyCode, Runnable runnable) {
		KEY_MAP.put(keyCode, new MyKeyEventBean(keyCode, false, false, false, runnable));
		activate();
	}

	public void remove(int keyCode) {
		KEY_MAP.remove(keyCode);
		activate();
	}

	private void registerHotKey(int keyCode, boolean isShiftDown, boolean isControlDown, boolean isAltDown, Runnable runnable) {
		KEY_MAP_REG.put(keyCode, runnable);

		int modifier = 0;
		// Shift
		if (isShiftDown) {
			modifier += JIntellitype.MOD_SHIFT;
		}
		// Ctrl
		if (isControlDown) {
			modifier += JIntellitype.MOD_CONTROL;
		}
		// Alt
		if (isAltDown) {
			modifier += JIntellitype.MOD_ALT;
		}
		JIntellitype.getInstance().registerHotKey(keyCode, modifier, keyCode);
		log.info("register keyCode:" + keyCode + " KeyChar:" + (char) keyCode);
	}

	public static void main(String[] args) {
		MyHotKeyHelper globalEventKey = new MyHotKeyHelper();
		globalEventKey.add(KeyEvent.VK_A, false, false, false, new Runnable() {
			public void run() {
				System.out.println("test");
			}
		});
	}

	class MyKeyEventBean {
		private int keyCode;
		private boolean isShiftDown;
		private boolean isControlDown;
		private boolean isAltDown;
		private Runnable runnable;

		public MyKeyEventBean(int keyCode, boolean isShiftDown, boolean isControlDown, boolean isAltDown, Runnable runnable) {
			this.keyCode = keyCode;
			this.isShiftDown = isShiftDown;
			this.isControlDown = isControlDown;
			this.isAltDown = isAltDown;
			this.runnable = runnable;
		}

		public int getKeyCode() {
			return keyCode;
		}

		public void setKeyCode(int keyCode) {
			this.keyCode = keyCode;
		}

		public boolean isShiftDown() {
			return isShiftDown;
		}

		public void setShiftDown(boolean isShiftDown) {
			this.isShiftDown = isShiftDown;
		}

		public boolean isControlDown() {
			return isControlDown;
		}

		public void setControlDown(boolean isControlDown) {
			this.isControlDown = isControlDown;
		}

		public boolean isAltDown() {
			return isAltDown;
		}

		public void setAltDown(boolean isAltDown) {
			this.isAltDown = isAltDown;
		}

		public Runnable getRunnable() {
			return runnable;
		}

		public void setRunnable(Runnable runnable) {
			this.runnable = runnable;
		}
	}
}
