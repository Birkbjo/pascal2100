package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Variable extends Factor {
	Expression expr;
	public Variable(int n) {
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
	
	public static Variable parse(Scanner s) {
		enterParser("variable");
		
		Variable v = new Variable(s.curLineNum());
		s.skip(TokenKind.nameToken);
		if(s.curToken.kind == TokenKind.leftBracketToken) {
			s.readNextToken();
			v.expr = Expression.parse(s);
			s.skip(TokenKind.rightBracketToken);
		} else {
			s.readNextToken();
		}
		
		leaveParser("variable");
		return v;
	}

}
