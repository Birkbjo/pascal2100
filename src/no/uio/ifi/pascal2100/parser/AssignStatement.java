package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class AssignStatement extends Statement {
	Variable var;
	Expression expr;
	AssignStatement(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<assign-statm> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static AssignStatement parse(Scanner s) {
		enterParser("assign-statm");
		
		AssignStatement as = new AssignStatement(s.curLineNum());
		as.var = Variable.parse(s);
		s.skip(TokenKind.assignToken);
		as.expr = Expression.parse(s);
		
		leaveParser("assign-statm");
		return as;
	}

}