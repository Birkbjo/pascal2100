package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

public class FactorOperator extends Operator {

	Token t;

	public FactorOperator(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<factor-operator> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(" " + t.kind.toString() + " ");
	}

	public static FactorOperator parse(Scanner s) {
		enterParser("factor opr");

		FactorOperator fo = new FactorOperator(s.curLineNum());
		if (s.curToken.kind.isFactorOpr()) {
			fo.t = s.curToken;
			s.readNextToken();
		} else {
			s.testError("factor-operator");
		}
		leaveParser("factor opr");
		return fo;
	}

	@Override
	public void genCode(CodeFile f) {
		//System.out.println("Factor Opreator");
		if(t.kind != TokenKind.divToken && t.kind != TokenKind.modToken) {
			f.genInstr("", "movl", "%eax,%ecx", "");
			f.genInstr("", "popl", "%eax", "");
			if(t.kind == TokenKind.multiplyToken) {
				f.genInstr("", "imull", "%ecx,%eax", "");
			} else if (t.kind == TokenKind.andToken) {
				f.genInstr("", "andl", "%ecx,%eax", "");
			}
			
		} else {
			f.genInstr("", "movl", "%eax,%ecx", "");
			f.genInstr("", "popl", "%eax", "");
			f.genInstr("", "cdq", "", "");
			if(t.kind == TokenKind.divToken) {
				f.genInstr("","idivl","%ecx","");
			} else if(t.kind == TokenKind.modToken) {
				f.genInstr("","idivl","%ecx","");
				f.genInstr("", "movl", "%edx,%eax", "mod in class factor Opr");
			}
		}
	}
}
