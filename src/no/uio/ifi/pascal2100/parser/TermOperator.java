package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

public class TermOperator extends Operator {
	Token operator;
	TermOperator(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<term-operator> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(" " + operator.kind.toString() + " ");
		
	}

	public static TermOperator parse(Scanner s) {
	enterParser("term opr");
		
		TermOperator to = new TermOperator(s.curLineNum());
		if(s.curToken.kind.isTermOpr()) {
			to.operator = s.curToken;
			s.readNextToken();
		} else {
			s.testError("term-operator");
		}
		leaveParser("term opr");
		return to;
		
	}

	@Override
	public void genCode(CodeFile f) {
		f.genInstr("", "movl", "%eax,%ecx", "");
		f.genInstr("", "popl", "%eax", "");
		switch (operator.kind) {
		case andToken:
			f.genInstr("", "andl", "%ecx,%eax", "  and");
		case orToken:
			f.genInstr("", "orl", "%ecx,%eax", "  or");
			break;
		case addToken:
			f.genInstr("", "addl", "%ecx,%eax", "  +");
			break;
		case subtractToken:
			f.genInstr("", "subl", "%ecx,%eax", "  -");
			break;
		}
		
			
	}
}
