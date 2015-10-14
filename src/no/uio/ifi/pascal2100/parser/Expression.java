package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Expression extends PascalSyntax {

	Expression(int n) {
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

	public static Expression parse(Scanner s) {
		enterParser("expression");
		Expression expr = new Expression(s.curLineNum());
		//TODO: implement expressions, quick fix for mini.pas.
		if(s.curToken.kind == TokenKind.stringValToken) {
			s.readNextToken();
		}
		leaveParser("expression");
		return expr;
	}

}