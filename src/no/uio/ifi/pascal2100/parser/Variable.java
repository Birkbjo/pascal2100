package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Variable extends Factor {
	Expression expr;
	String name;
	public Variable(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<variable> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
		if(expr != null) {
			Main.log.prettyPrint("[");
			expr.prettyPrint();
			Main.log.prettyPrint("]");
		}
	}
	
	public static Variable parse(Scanner s) {
		enterParser("variable");
		
		Variable v = new Variable(s.curLineNum());
		s.test(TokenKind.nameToken);
		v.name = s.curToken.id;
		s.readNextToken();
		
		if(s.curToken.kind == TokenKind.leftBracketToken) {
			s.readNextToken();
			v.expr = Expression.parse(s);
			s.skip(TokenKind.rightBracketToken);
		}

		leaveParser("variable");
		return v;
	}

}
