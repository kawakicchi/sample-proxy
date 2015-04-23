package com.github.kawakicchi.sql;

import java.io.IOException;

public class BasicTokenAnalyzer extends AbstractTokenAnalyzer {

	// escape
	protected boolean isEscapeCharacter(final char c, final char type) {
		return (c == '\\');
	}

	// 
	protected boolean isStringCharacter(final char c) {
		return (c == '"');
	}

	protected boolean isReserveCharacter(final char c) {
		return (('\r' == c) || ('\n' == c) || ('\t' == c) || (' ' == c));
	}

	private static final int PHASE_NONE = 0;
	private static final int PHASE_STRING = 1;

	protected void doAnalyze() throws IOException {

		Character c;

		int phase = PHASE_NONE;

		char stringChar = 0;
		StringBuilder buffer = new StringBuilder();
		while (null != (c = next())) {
			switch (phase) {

			case PHASE_STRING: {
				if (isEscapeCharacter(c, stringChar)) {
					Character c2 = next();
					if (null == c2) {
						// エスケープ文字で次がない（そうそうあり得ない）
						// または、エスケープと文字区切りが同じ場合あり得る。
						buffer.append(c);
						if (0 < buffer.length()) {
							addToken(buffer.toString());
							buffer = new StringBuilder();
						}
					} else {
						if (stringChar == c2) {
							// エスケープ + 文字区切り
							// 通常エスケープ処理
							buffer.append(c);
							buffer.append(c2);
						} else {
							// エスケープ + 文字区切り以外
							if (stringChar == c) {
								// エスケープと文字列が同じ場合
								phase = PHASE_NONE;

								buffer.append(c);
								if (0 < buffer.length()) {
									addToken(buffer.toString());
									buffer = new StringBuilder();
								}

								back();
							} else {
								// 例外　ありえないはず・・・
								buffer.append(c);
								buffer.append(c2);
							}
						}
					}
				} else if (stringChar == c) {
					// コメント終了
					buffer.append(c);
					if (0 < buffer.length()) {
						addToken(buffer.toString());
						buffer = new StringBuilder();
					}
					phase = PHASE_NONE;
				} else {
					buffer.append(c);
				}
				break;
			}

			default: {
				if (isStringCharacter(c)) {
					if (0 < buffer.length()) {
						addToken(buffer.toString());
						buffer = new StringBuilder();
					}
					stringChar = c;
					buffer.append(c);
					phase = PHASE_STRING;
				} else if (isReserveCharacter(c)) {
					if (0 < buffer.length()) {
						addToken(buffer.toString());
						buffer = new StringBuilder();
					}
					buffer.append(c);
					addToken(buffer.toString());
					buffer = new StringBuilder();

				} else {
					buffer.append(c);
				}
				break;
			}

			}

		}

		if (0 < buffer.length()) {
			addToken(buffer.toString());
		}
	}

	private void addToken(final String value) {
		//System.out.println("======================================================");
		System.out.println(value);
	}
}
