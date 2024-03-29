package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class AssignStatement extends Statement {
	Variable var;
	PascalDecl varRef;
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
		varRef = (PascalDecl) curScope.findDecl(var.name, this);
		var.check(curScope, lib);
		expr.check(curScope, lib);
	}

	@Override
	public void genCode(CodeFile f) {
		expr.genCode(f);
		if(varRef instanceof VarDecl) {
			int off1 = -4*varRef.declLevel;
			int off2 = varRef.declOffset;
			f.genInstr("", "movl", off1+"(%ebp),%edx", "");
			f.genInstr("", "movl", "%eax,"+off2+"(%edx)", varRef.name + " :=");
		} else if(varRef instanceof FuncDecl) { // funcdecl
			f.genInstr("", "movl", "%eax,-32(%edp)", "");
		}
	}
}