package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Name extends Constant {

	Name(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<name> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static Name parse(Scanner s) {
		enterParser("name");
		s.test(TokenKind.nameToken);
		Name n = new Name(s.curLineNum());
		s.readNextToken();
		leaveParser("name");
		return n;
	}
	

}
