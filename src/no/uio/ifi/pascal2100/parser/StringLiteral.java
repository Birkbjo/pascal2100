package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class StringLiteral extends Constant {

	public StringLiteral(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<char-literal> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static StringLiteral parse(Scanner s) {
		enterParser("char-literal");
		s.test(TokenKind.stringValToken);
		StringLiteral sl = new StringLiteral(s.curLineNum());
		s.readNextToken();
		leaveParser("char-literal");
		return sl;
	}

}
