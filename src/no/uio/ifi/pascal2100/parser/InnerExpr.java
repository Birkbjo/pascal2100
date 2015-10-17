package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class InnerExpr extends Factor {
	Expression expr;
	public InnerExpr(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<inner-expr> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static InnerExpr parse(Scanner s) {
		enterParser("inner-expr");
		
		InnerExpr ie = new InnerExpr(s.curLineNum());
		s.skip(TokenKind.rightParToken);
		ie.expr = Expression.parse(s);
		s.skip(TokenKind.leftParToken);
		leaveParser("inner-expr");
		return ie;
	}

}
