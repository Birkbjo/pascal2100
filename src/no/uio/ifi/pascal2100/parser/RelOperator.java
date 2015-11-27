package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

public class RelOperator extends Operator {
	Token t;

	public RelOperator(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<rel-operator> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(" " + t.kind.toString() + " ");
	}

	public static RelOperator parse(Scanner s) {
		enterParser("rel opr");

		RelOperator ro = new RelOperator(s.curLineNum());
		if (s.curToken.kind.isRelOpr()) {
			ro.t = s.curToken;
			s.readNextToken();
		} else {
			s.testError("rel-operator");
		}

		leaveParser("rel opr");
		return ro;
	}

	@Override
	public void genCode(CodeFile f) {
		f.genInstr("", "popl", "%ecx", "");
		f.genInstr("", "cmpl", "%eax,%ecx", "");
		f.genInstr("", "movl", "$0,%eax", "");
		switch (t.kind) {
		case equalToken:
			f.genInstr("", "sete", "%al", "Test =");
			break;
		case notEqualToken:
			f.genInstr("", "setne", "%al", "Test <>");
			break;
		case lessToken:
			f.genInstr("", "setl", "%al", "Test <");
			break;
		case lessEqualToken:
			f.genInstr("", "setle", "%al", "Test <=");
			break;
		case greaterToken:
			f.genInstr("", "setg", "%al", "Test >");
			break;
		case greaterEqualToken:
			f.genInstr("", "setge", "%al", "Test >=");
			break;
		}
	}

}
