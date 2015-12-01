package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Expression extends PascalSyntax {
	SimpleExpr expr1;
	RelOperator relopr;
	SimpleExpr exp2;
	
	Expression(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<expression> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		expr1.prettyPrint();
		if(relopr != null) {
			relopr.prettyPrint();
			exp2.prettyPrint();
		}

	}

	public static Expression parse(Scanner s) {
		enterParser("expression");
		Expression expr = new Expression(s.curLineNum());
		expr.expr1 = SimpleExpr.parse(s);

		if (s.curToken.kind.isRelOpr()) {
			expr.relopr = RelOperator.parse(s);
			expr.exp2 = SimpleExpr.parse(s);
		}

		leaveParser("expression");
		return expr;
	}

	public void check(Block curScope, Library lib) {
		expr1.check(curScope,lib);
		if(exp2 != null) {
			exp2.check(curScope,lib);
		}
		
		
	}

	@Override
	public void genCode(CodeFile f) {
		expr1.genCode(f);
		if (relopr != null) {
		    f.genInstr("", "pushl", "%eax", "Expression");
		    exp2.genCode(f);
		    relopr.genCode(f);
		}
	}

}