package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class InnerExpr extends Factor {
	Expression expr;
	public InnerExpr(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<inner-expr> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("(");
		expr.prettyPrint();
		Main.log.prettyPrint(")");
		
	}
	
	public static InnerExpr parse(Scanner s) {
		enterParser("inner-expr");
		
		InnerExpr ie = new InnerExpr(s.curLineNum());
		s.skip(TokenKind.leftParToken);
		ie.expr = Expression.parse(s);
		s.skip(TokenKind.rightParToken);
		leaveParser("inner-expr");
		return ie;
	}

}
