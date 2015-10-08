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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static StringLiteral parse(Scanner s) {
		enterParser("string-literal");
		s.test(TokenKind.stringValToken);
		StringLiteral sl = new StringLiteral(s.curLineNum());
		s.readNextToken();
		leaveParser("string-literal");
		return sl;
	}

}
