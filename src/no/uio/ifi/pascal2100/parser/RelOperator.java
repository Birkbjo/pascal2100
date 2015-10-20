package no.uio.ifi.pascal2100.parser;

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
		enterParser("rel-operator");
		
		RelOperator ro = new RelOperator(s.curLineNum());
		if(s.curToken.kind.isRelOpr()) {
			ro.t = s.curToken;
			s.readNextToken();
		} else {
			s.testError("rel-operator");
		}
		
		leaveParser("rel-operator");
		return ro;
	}

}
