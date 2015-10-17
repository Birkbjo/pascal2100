package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class FactorOperator extends Operator {

	public FactorOperator(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<factor-operator> on line " +lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}


	public static FactorOperator parse(Scanner s) {
		enterParser("factor-operator");
		
		FactorOperator fo = new FactorOperator(s.curLineNum());
		if(s.curToken.kind.isFactorOpr()) {
			s.readNextToken();
		} else {
			s.testError("factor-operator");
		}
		leaveParser("factor-operator");
		return fo;
	}

}
