package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Expression extends PascalSyntax {
	SimpleExpr expr1;
	RelOperator relopr;
	SimpleExpr exp2;
	Expression(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<expression> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}

	public static Expression parse(Scanner s) {
		enterParser("expression");
		Expression expr = new Expression(s.curLineNum());
		expr.expr1 = SimpleExpr.parse(s);

		if(s.curToken.kind.isRelOpr()) {
			expr.relopr = RelOperator.parse(s);
			expr.exp2 = SimpleExpr.parse(s);
		}

		leaveParser("expression");
		return expr;
	}

}