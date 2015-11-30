package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class AssignStatement extends Statement {
	Variable var;
	Expression expr;

	AssignStatement(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<assign statm> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		var.prettyPrint();
		Main.log.prettyPrint(" := ");
		expr.prettyPrint();

	}

	public static AssignStatement parse(Scanner s) {
		enterParser("assign statm");

		AssignStatement as = new AssignStatement(s.curLineNum());
		as.var = Variable.parse(s);
		s.skip(TokenKind.assignToken);
		as.expr = Expression.parse(s);

		leaveParser("assign statm");
		return as;
	}

	@Override
	void check(Block curScope, Library lib) {
		var.check(curScope, lib);
		expr.check(curScope, lib);
	}

	@Override
	public void genCode(CodeFile f) {
		if(var != null){
			var.genCode(f);
		}
	}
}