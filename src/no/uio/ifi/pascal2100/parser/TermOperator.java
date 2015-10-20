package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;

public class TermOperator extends Operator {
	String operator;
	TermOperator(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<term-operator> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(" " + operator + " ");
		
	}

	public static TermOperator parse(Scanner s) {
	enterParser("term-operator");
		
		TermOperator to = new TermOperator(s.curLineNum());
		if(s.curToken.kind.isTermOpr()) {
			to.operator = s.curToken.id;
			s.readNextToken();
		} else {
			s.testError("term-operator");
		}
		leaveParser("term-operator");
		return to;
		
	}

}
