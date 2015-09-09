package no.uio.ifi.pascal2100.scanner;

import no.uio.ifi.pascal2100.main.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

import java.io.*;

public class Scanner {
	public Token curToken = null, nextToken = null;

	private LineNumberReader sourceFile = null;
	private String sourceFileName, sourceLine = "";
	private int sourcePos = 0;

	public Scanner(String fileName) {
		sourceFileName = fileName;
		try {
			sourceFile = new LineNumberReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			Main.error("Cannot read " + fileName + "!");
		}

		readNextToken();
		readNextToken();
	}

	public String identify() {
		return "Scanner reading " + sourceFileName;
	}

	public int curLineNum() {
		return curToken.lineNum;
	}

	private void error(String message) {
		Main.error("Scanner error on line " + curLineNum() + ": " + message);
	}

	public void readNextToken() {
		curToken = nextToken;
		while (nextToken == null) {
			if (sourcePos >= sourceLine.length()) {
				readNextLine();
			}
			if (sourceLine.equals("")) {
				nextToken = new Token(eofToken, getFileLineNum());
				break;
			}
			char curC = getChar();
			String doubleTok = getDoubleChar();
			
			TokenKind curTokenType = checkSingleTokenType(curC);
			if (curC == '{') {
				skipComment("{");
			} else if(doubleTok.equals("/*")) {
				skipComment("/*");
			}
				
			if (curC == '\'') {
				// string literal
			} //else if() {
				// check double tokens
			// }
			else if (curTokenType != null) { // Check if single character is token
				nextToken = new Token(curTokenType, getFileLineNum());
				break;
			} else { // read name
				String name = "";
				while (sourcePos < sourceLine.length()) {

					char c = getChar();
					if (isLetterAZ(c) || isDigit(sourceLine.charAt(c))) {
						name += c;
						sourcePos++;
					} else if (isBlank(c)) {
						break;
					}
				}
				nextToken = new Token(name, getFileLineNum());
				break;
			}

		}

		Main.log.noteToken(nextToken);
	}
	
	private char getChar() {
		return sourceLine.charAt(sourcePos);
	}
	
	private String getDoubleChar() {
		if (sourcePos + 1 < sourceLine.length()) {
			return ""+sourceLine.charAt(sourcePos)+sourceLine.charAt(sourcePos+1);
		} else {
			return ""+sourceLine.charAt(sourcePos);
		}
		
	}
	
	private void skipComment(String commentType) {
		while (sourceLine.contains("}") || sourceLine.contains("*/")) {
			readNextLine();
			//sourcePos = sourceLine.indexOf(ch)
		}
	}

	private TokenKind checkSingleTokenType(char c) {
		for (TokenKind tk : TokenKind.values()) {
			if (c == tk.toString().charAt(0)) {
				return tk;
			}
		}
		return null;
	}

	private TokenKind checkTokenType(String s) {
		for (TokenKind tk : TokenKind.values()) {
			if (s.equals(tk.toString())) {
				return tk;
			}
		}
		return null;
	}

	private void readNextLine() {
		if (sourceFile != null) {
			try {
				sourceLine = sourceFile.readLine();
				if (sourceLine == null) {
					sourceFile.close();
					sourceFile = null;
					sourceLine = "";
				} else {
					sourceLine += " ";
				}
				sourcePos = 0;
			} catch (IOException e) {
				Main.error("Scanner error: unspecified I/O error!");
			}
		}
		if (sourceFile != null)
			Main.log.noteSourceLine(getFileLineNum(), sourceLine);
	}

	private int getFileLineNum() {
		return (sourceFile != null ? sourceFile.getLineNumber() : 0);
	}

	// Character test utilities:

	private boolean isLetterAZ(char c) {
		return 'A' <= c && c <= 'Z' || 'a' <= c && c <= 'z';
	}

	private boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}

	private boolean isNumber(String d) {
		while (isDigit(sourceLine.charAt(sourcePos))) {

		}
		return true;
	}

	private boolean isBlank(char c) {
		return c == ' ';
	}

	// Parser tests:

	public void test(TokenKind t) {
		if (curToken.kind != t)
			testError(t.toString());
	}

	public void testError(String message) {
		Main.error(curLineNum(), "Expected a " + message + " but found a "
				+ curToken.kind + "!");
	}

	public void skip(TokenKind t) {
		test(t);
		readNextToken();
	}
}
