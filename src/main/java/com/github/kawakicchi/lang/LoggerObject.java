package com.github.kawakicchi.lang;

import org.apache.log4j.Logger;

/**
 * このクラスは、ログ出力機能を備えたクラスです。
 * 
 * @author SCSK-KawakitaN
 */
public class LoggerObject {

	/** ロガー */
	private Logger log;

	public LoggerObject() {
		log = Logger.getLogger(this.getClass());
	}

	public LoggerObject(final Class<?> clazz) {
		log = Logger.getLogger(clazz);
	}

	public void setLogger(final Logger logger) {
		log = logger;
	}

	protected final void debug(final String message) {
		log.debug(message);
	}

	protected final void info(final String message) {
		log.info(message);
	}

	protected final void warn(final String message) {
		log.warn(message);
	}

	protected final void error(final String message) {
		log.error(message);
	}

	protected final void error(final String message, final Throwable t) {
		log.error(message, t);
	}

	protected final void fatal(final String message) {
		log.fatal(message);
	}

	protected final void fatal(final String message, final Throwable t) {
		log.fatal(message, t);
	}
}
