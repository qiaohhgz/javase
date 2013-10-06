package com.jim.mylogger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class MyLogger {
	public static final Logger myLogger = Logger.getLogger(MyLogger.class);
	private Logger log;

	public MyLogger(Logger log) {
		this.log = log;
	}

	// ///////////////////
	public void debug(Object message) {
		forcedLog(Level.DEBUG, message, null);
	}

	public void debug(Object message, Throwable t) {
		forcedLog(Level.DEBUG, message, t);
	}

	public void error(Object message) {
		forcedLog(Level.ERROR, message, null);
	}

	public void error(Object message, Throwable t) {
		forcedLog(Level.ERROR, message, t);
	}

	public void fatal(Object message) {
		forcedLog(Level.FATAL, message, null);
	}

	public void fatal(Object message, Throwable t) {
		forcedLog(Level.FATAL, message, t);
	}

	public void info(Object message) {
		forcedLog(Level.INFO, message, null);
	}

	public void info(Object message, Throwable t) {
		forcedLog(Level.INFO, message, t);
	}

	public void warn(Object message) {
		forcedLog(Level.WARN, message, null);
	}

	public void warn(Object message, Throwable t) {
		forcedLog(Level.WARN, message, t);
	}

	// ///////////////////
	protected void forcedLog(Level level, Object message, Throwable t) {
		try {
			if (message instanceof Throwable) {
				((Throwable) message).printStackTrace(new PrintStream("myLogger.log"));
			}
			if (t != null) {
				t.printStackTrace(new PrintStream("myLogger.log"));
			}
		} catch (FileNotFoundException e) {
			myLogger.error(e);
		}
		switch (level.toInt()) {
		case Level.DEBUG_INT: {
			log.debug(message, t);
			break;
		}
		case Level.INFO_INT: {
			log.info(message, t);
			break;
		}
		case Level.WARN_INT: {
			log.warn(message, t);
			break;
		}
		case Level.ERROR_INT: {
			log.error(message, t);
			break;
		}
		case Level.FATAL_INT: {
			log.fatal(message, t);
			break;
		}
		}
	}

	public void write(OutputStream os, InputStream is, int bsLength) throws IOException {
		int size = 0;
		byte[] bs = new byte[bsLength];
		while (-1 != (size = is.read(bs))) {
			os.write(bs, 0, size);
		}
		os.flush();
		os.close();
		is.close();
	}

	static public MyLogger getLogger(String name) {
		return new MyLogger(Logger.getLogger(name));
	}

	static public MyLogger getLogger(Class<?> clazz) {
		return new MyLogger(Logger.getLogger(clazz));
	}

	static public MyLogger getLogger(String name, LoggerFactory factory) {
		return new MyLogger(Logger.getLogger(name, factory));
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
}
