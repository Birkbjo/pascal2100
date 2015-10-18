package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

public class RelOperator extends Operator {

	public RelOperator(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<rel-operator> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}

	public static RelOperator parse(Scanner s) {
		enterParser("rel-operator");
		
		RelOperator ro = new RelOperator(s.curLineNum());
		if(s.curToken.kind.isRelOpr()) {
			s.readNextToken();
		} else {
			s.testError("rel-operator");
		}
		
		leaveParser("rel-operator");
		return ro;
	}

}
