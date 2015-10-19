package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class NumericLiteral extends Constant {

	public NumericLiteral(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<numeric-literal> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static NumericLiteral parse(Scanner s) {
		enterParser("numeric-literal");
		s.test(TokenKind.intValToken);
		NumericLiteral nl = new NumericLiteral(s.curLineNum());
		s.readNextToken();
		
		leaveParser("numeric-literal");
		return nl;
	}

}
