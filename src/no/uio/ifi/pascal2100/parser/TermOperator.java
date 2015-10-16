package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

public class TermOperator extends Operator {

	TermOperator(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<term-operator> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}

	public static TermOperator parse(Scanner s) {
	enterParser("term-operator");
		
		TermOperator to = new TermOperator(s.curLineNum());
		if(s.curToken.kind.isTermOpr()) {
			s.readNextToken();
		} else {
			s.testError("term-operator");
		}
		leaveParser("term-operator");
		return to;
		
	}

}
