package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

public class FactorOperator extends Operator {

	Token t;
	public FactorOperator(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<factor-operator> on line " +lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(" " + t.id + " ");		
	}


	public static FactorOperator parse(Scanner s) {
		enterParser("factor-operator");
		
		FactorOperator fo = new FactorOperator(s.curLineNum());
		if(s.curToken.kind.isFactorOpr()) {
			fo.t = s.curToken;
			s.readNextToken();
		} else {
			s.testError("factor-operator");
		}
		leaveParser("factor-operator");
		return fo;
	}

}
