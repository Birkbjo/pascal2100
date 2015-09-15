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

	/*TODO: FIX sourcepos++ and split into methods.*/
	public void readNextToken() {
		curToken = nextToken;
		nextToken = null;
		while (nextToken == null) {
			if (sourcePos >= sourceLine.length()) {
				readNextLine();
			}
			if(sourceFile == null) {
				nextToken = new Token(eofToken, getFileLineNum());
				break;
			}
			
			System.out.println(sourceLine);
			
			while (isBlank(getChar())) {
				System.out.println(getChar() + " is blank, pos: " + sourcePos);
				if (sourcePos+1 < sourceLine.length()) {
					sourcePos++;										
				} else {
					readNextLine();
				}
			}
			if (sourceLine.equals("")) {
				nextToken = new Token(eofToken, getFileLineNum());
				break;
			}
			char curC = getChar();
			String doubleTok = getDoubleChar();
			System.out.println("curC is " + curC + " getchar is " + getChar());
			if (curC == '{') {
				skipComment("{");
				continue;
			} else if (doubleTok.equals("/*")) {
				skipComment("/*");
				continue;
			}
			if (isDelim(curC)) {
				System.out.println((int)curC + " is delim " + sourceLine + " sp " + sourcePos);
				TokenKind tokTypeDouble = checkTokenType(doubleTok);
				TokenKind tokTypeSingle;
				if (curC == '\'') {
					System.out.println("Start of string literal");
					String stringVal = "";
					sourcePos++;
					while (sourcePos < sourceLine.length() && getChar() != '\'') {
						
						char c = getChar();
						System.out.println(""+c);
						stringVal += c;
						sourcePos++;
					}
					if(sourcePos == sourceLine.length()) { //out of bounds, not seen a "'" yet.
						error("Text string without end!");
					}
					nextToken = new Token(null,stringVal,getFileLineNum());
					break;

				} else if (tokTypeDouble != null) {
					System.out.println("DOUBLETOKEN: " + tokTypeDouble);
					sourcePos++;
					nextToken = new Token(tokTypeDouble, getFileLineNum());
					break;
				} else if ((tokTypeSingle = checkSingleTokenType(curC)) != null) {
					nextToken = new Token(tokTypeSingle, getFileLineNum());
					System.out.println(nextToken.identify());
					break;
				} else {
					error("Unknown symbol: " + getChar());
				}
			} else if(isDigit(curC)) {
				String num = readNumber();
				nextToken = new Token(Integer.parseInt(num),getFileLineNum());
			} else { // read word
				System.out.println("getchar is " + getChar());
				String word = "";
				while (sourcePos < sourceLine.length() && !isDelim(getChar())) {
					char c = getChar();
					
					if (isLetterAZ(c) || isDigit(c)) {
						word += c;
						sourcePos++;
					} else {
						error("Illegal character: '" + c +"'");
					}
				}
				sourcePos--; //sourcePos is incremented after loop.
				nextToken = new Token(word, getFileLineNum());
				System.out.println(nextToken.identify());
				break;
			}
		}
		sourcePos++;
		Main.log.noteToken(nextToken);
	}

	private char readNextChar() {
		char c = sourceLine.charAt(sourcePos);
		int pos = 0;
		while (c == ' ') {
			if (sourcePos >= sourceLine.length()) {
				readNextLine();
				pos = 0;
			} else {
				c = sourceLine.charAt(pos++);
			}
		}
		return c;
	}

	private char getChar() {
		if(sourcePos < sourceLine.length()) {
			return sourceLine.charAt(sourcePos);
		}
		return 0;
	}

	private String getDoubleChar() {
		if (sourcePos + 1 < sourceLine.length()) {
			return "" + sourceLine.charAt(sourcePos)
					+ sourceLine.charAt(sourcePos + 1);
		} else if(sourcePos < sourceLine.length()) {
			return ""+sourceLine.charAt(sourcePos);
		} else return "";

	}

	private void skipComment(String commentType) {
		int commentLineNr = getFileLineNum();
		if(commentType.equals("/*")) {
			while (!sourceLine.contains("*/")) {
				if(sourceLine.equals("")) {
					error("No end for comment starting on line " + commentLineNr);
				}
				readNextLine();
			}
			sourcePos = sourceLine.indexOf("*/",sourcePos)+2;
		} else if(commentType.equals("{")) {
			while (!sourceLine.contains("}")) {
				if(sourceLine.equals("")) {
					error("No end for comment starting on line " + commentLineNr);
				}
				readNextLine();	
			}
			sourcePos = sourceLine.indexOf("}",sourcePos)+1;
			
		}
		
		//jump at start on next line.
		//readNextLine();
	}

	private TokenKind checkSingleTokenType(char c) {
		for (TokenKind tk : TokenKind.values()) {
			if (tk.toString().length() == 1 && c == tk.toString().charAt(0)) {
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
		String nr = "";
		while (isDigit(getChar())) {
			nr+= getChar();
			sourcePos++;
		}
		return true;
	}
	
	private String readNumber() {
		String nr = "";
		while (isDigit(getChar())) {
			nr+= getChar();
			sourcePos++;
		}
		sourcePos--;
		return nr;
	}

	private boolean isDelim(char c) {
		char delims[] = { ';', ':', '(', ')', '[', ']', '<', '>', ' ', ',',
				'=', '+', '-', '*', '"', '\'','.' };
		for (char del : delims) {
			if (c == del) {
				return true;
			}
		}
		return false;
	}

	private boolean isBlank(char c) {
		return c == ' ' || c == '\t';
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
