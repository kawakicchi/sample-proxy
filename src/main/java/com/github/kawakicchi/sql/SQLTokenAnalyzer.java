package com.github.kawakicchi.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SQLTokenAnalyzer extends BasicTokenAnalyzer {

	public static void main(final String[] args) {
		File file = new File(args[0]);

		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(file), "Windows-31J");

			TokenAnalyzer analyzer = new SQLTokenAnalyzer();
			analyzer.analyze(reader);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	protected boolean isEscapeCharacter(final char c, final char type) {
		if ('\"' == type) {
			return (c == '\\');
		} else if ('\'' == type) {
			return (c == '\'');
		}
		return false;
	}

	@Override
	protected boolean isStringCharacter(final char c) {
		return ((c == '\"') || (c == '\''));
	}

	@Override
	protected boolean isReserveCharacter(final char c) {
		return (('\r' == c) || ('\n' == c) || ('\t' == c) || (' ' == c) || (',' == c) || ('(' == c) || (')' == c) || ('=' == c) || ('<' == c) || ('>' == c)
				|| ('&' == c) || ('|' == c) || (':' == c) || ('.' == c));
	}

}
