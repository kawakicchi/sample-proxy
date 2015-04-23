package com.github.kawakicchi.sql;

import java.io.IOException;
import java.io.Reader;

import com.github.kawakicchi.lang.LoggerObject;

public abstract class AbstractTokenAnalyzer extends LoggerObject implements TokenAnalyzer {

	private Reader reader;
	private static final int BUFFER_SIZE = 1024;

	private char[] buffer;

	private int index;
	private StringBuilder string;

	public void analyze(final Reader reader) throws IOException {
		this.reader = reader;

		buffer = new char[BUFFER_SIZE];

		index = 0;
		string = new StringBuilder();
		doAnalyze();
	}

	protected abstract void doAnalyze() throws IOException;

	protected void back() {
		if (0 < index) {
			index--;
		}
	}

	protected Character next() throws IOException {
		Character c = null;
		if (string.length() <= index) {
			int readSize = reader.read(buffer, 0, BUFFER_SIZE);
			if (0 < readSize) {
				string.append(buffer, 0, readSize);
			}
		}

		if (string.length() > index) {
			c = string.charAt(index);
			index++;
		}
		return c;
	}
}
