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

	/**
	 * Reads the next token, and set nextToken pointer
	 * to this token. Breaks out of the while-loop as soon as a
	 * token is identified.
	 */
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
			whileBlank(); //skip blanks.

			if (sourceLine.equals("")) { //Check for eof here, as whileBlank() reads new lines.
				nextToken = new Token(eofToken, getFileLineNum());
				break;
			}
		
			char curC = getChar();
			String doubleTok = getDoubleChar();
			if (curC == '{') {
				skipComment("{");
				continue; //go to start of while, as we need to check for blanks. etc.
			} else if (doubleTok.equals("/*")) {
				skipComment("/*");
				continue;
			}
			if (isSymbol(curC)) {
				TokenKind tokTypeDouble = checkTokenType(doubleTok);
				TokenKind tokTypeSingle;
				if (curC == '\'') {
					sourcePos++; //skip to start of String literal
					
					String stringVal = readStringLiteral();
					
					nextToken = new Token(null,stringVal,getFileLineNum());
					break;

				} else if (tokTypeDouble != null) {
					sourcePos++; //two chars - skip one extra
					nextToken = new Token(tokTypeDouble, getFileLineNum());
					break;
				} else if ((tokTypeSingle = checkSingleTokenType(curC)) != null) {
					nextToken = new Token(tokTypeSingle, getFileLineNum());
					break;
				} else {
					error("Unknown symbol: " + getChar());
				}
			} else if(isDigit(curC)) {
				String num = readNumber();
				nextToken = new Token(Integer.parseInt(num),getFileLineNum());
			} else if(isDigitOrLetter(curC)) { // read word
				String word = readName();			
				nextToken = new Token(word, getFileLineNum());
				break;
			} else {
				error("Illegal character: '" + curC +"'");
			}
		}
		sourcePos++;
		Main.log.noteToken(nextToken);
	}
	
	/**
	 * Reads a name token from current sourcePos,
	 * stops reading when current char is not a letter nor a digit.
	 * 
	 * @return - A string of the token-name.
	 */
	private String readName() {
		String name = "";
		while (sourcePos < sourceLine.length() && isDigitOrLetter(getChar())) {
			char c = getChar();				
			name += c;
			sourcePos++;
		}
		sourcePos--; //sourcePos is incremented after while-loop in readNextToken()
		return name;
	}
	
	/**
	 * Skips all blanks, if we are at end of line, we read a new line
	 * and skip blanks here as well. Updates sourcePos to next char that is not a blank.
	 */
	private void whileBlank() {
		while (isBlank(getChar())) {
			if (sourcePos+1 < sourceLine.length()) {
				sourcePos++;										
			} else {
				readNextLine();
			}
		}
	}
	
	/**
	 * Reads a string literal. Reads until a ' is found.
	 * If we go out of bounds of current line, we throw an error.
	 * Note that the sourcePos should be incremented before this is called,
	 * so that getChar() returns the char after the start of the string literal; "'".
	 * @return - The String literal represented by a string.
	 */
	private String readStringLiteral() {
		String stringVal ="";
		while (sourcePos < sourceLine.length()) {
			//Support for ' inside a string literal:
			if(getChar() == '\'' && sourceLine.charAt(sourcePos+1) == '\'') {
				stringVal += getChar();
				sourcePos+=2;
				continue;
			} else if(getChar() == '\'') { //end of string literal.
				break;
			}
			char c = getChar();
			stringVal += c;
			sourcePos++;
		}
		
		if(sourcePos == sourceLine.length()) { //out of bounds, not seen a "'" yet.
			error("Text string without end!");
		}
		return stringVal;
	}
	
	/**
	 * Gets the current char according to sourcePos and sourceLine.
	 * Note that sourcePos is not updated.
	 * @return - the current char.
	 */
	private char getChar() {
		if(sourcePos < sourceLine.length()) {
			return sourceLine.charAt(sourcePos);
		}
		return 0;
	}

	/**
	 * Attempts to get the currentChar and the character after, in order
	 * to check for double-tokens.
	 * If current char is the last of the line, we return an empty string concatenated
	 * with the current char.
	 * Not that sourcePos is never updated here.
	 * @return - A String of currentChar and the character after, or a string with the current char
	 * if we are at end of line.
	 */
	private String getDoubleChar() {
		if (sourcePos + 1 < sourceLine.length()) {
			return "" + sourceLine.charAt(sourcePos)
					+ sourceLine.charAt(sourcePos + 1);
		} else if(sourcePos < sourceLine.length()) {
			return ""+sourceLine.charAt(sourcePos);
		} else return "";

	}
	
	/**
	 * Attempts to skip comments. A comment-check should be done before
	 * this is called, and called with the start of the commenttype, like /* or {.
	 * SourcePos is updated so that it points to the next character after the end of comment.
	 * If we read until end of file without finding the end of the comment, we throw an error.
	 * @param commentType - the start of the commenttype that we should skip.
	 */
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
	}
	
	/**
	 * Loops through the Tokens with length = 1. in order to check
	 * if given character is equal to any of the tokens.
	 * @param c - character to check for equality.
	 * @return - The TokenKind that the current char represents, or null if 
	 * no TokenKinds are found.
	 */
	private TokenKind checkSingleTokenType(char c) {
		for (TokenKind tk : TokenKind.values()) {
			if (tk.toString().length() == 1 && c == tk.toString().charAt(0)) {
				return tk;
			}
		}
		return null;
	}
	
	/**
	 * Same as CheckSingleTokenType, other than this checks for a String to
	 * see if the given S is equal to any TokenKind.
	 * @param s - String to check for equality of a TokenKind.
	 * @return - The TokenKind that String s represents, null if no TokenKinds are equal.
	 */
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
	
	private boolean isDigitOrLetter(char c) {
		return isLetterAZ(c) || isDigit(c);
	}
	
	/**
	 * Reads a number from current sourcePos, note that a check for 
	 * isDigit() should be called before this.
	 * @return - A String representing a number (0-9 chars).
	 */
	private String readNumber() {
		String nr = "";
		while (isDigit(getChar())) {
			nr+= getChar();
			sourcePos++;
		}
		sourcePos--; //sourcePos is incremented after while-loop in readNextToken()
		return nr;
	}

	/**
	 * Check if the char is a symbol in pascal.
	 * @param c - char to check for.
	 * @return - True char c is a symbol in pascal, false otherwise.
	 */
	private boolean isSymbol(char c) {
		char delims[] = { ';', ':', '(', ')', '[', ']', '<', '>', ',',
				'=', '+', '-', '*', '\'','.' };
		for (char del : delims) {
			if (c == del) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if current c is blank, that is space or tab.
	 * @param c - Char to check for blank.
	 * @return - True if char c is blank, false otherwise.
	 */
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
