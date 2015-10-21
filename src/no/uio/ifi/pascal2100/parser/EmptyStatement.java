package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

class EmptyStatement extends Statement {

	EmptyStatement(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<empty-statm> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		//Do nothing
	}
	
	public static EmptyStatement parse(Scanner s) {
		enterParser("empty statm");
		EmptyStatement es = new EmptyStatement(s.curLineNum());
		leaveParser("empty statm");
		return es;
	}
}