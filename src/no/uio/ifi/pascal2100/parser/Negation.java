package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Negation extends Factor {
	Factor factor;
	public Negation(int n) {
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
	
	public static Negation parse(Scanner s) {
		enterParser("negation");
		
		Negation n = new Negation(s.curLineNum());
		s.skip(TokenKind.notToken);
		n.factor = Factor.parse(s);
		
		
		leaveParser("negation");
		return n;
		
	}

}
