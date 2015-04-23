package com.github.kawakicchi.sql;

import java.io.IOException;
import java.io.Reader;

/**
 * このインターフェースは、トークン解析機能を定義するためのインターフェースです。
 * 
 * @author SCSK-KawakitaN
 */
public interface TokenAnalyzer {

	public void analyze(final Reader reader) throws IOException;
}
