package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class PrefixOperator extends Operator {

	public PrefixOperator(int n) {
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

	public static PrefixOperator parse(Scanner s) {
		enterParser("prefix-operator");
		PrefixOperator preopr = new PrefixOperator(s.curLineNum());
		if(s.curToken.kind == TokenKind.addToken) {
			s.skip(TokenKind.addToken);
		} else {
			s.skip(TokenKind.subtractToken);
		}
		
		
		leaveParser("prefix-operator");
		return preopr;
	}

}
