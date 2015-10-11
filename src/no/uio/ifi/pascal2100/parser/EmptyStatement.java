package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

class EmptyStatement extends Statement {

	EmptyStatement(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static EmptyStatement parse(Scanner s) {
		enterParser("empty-statement");
		EmptyStatement es = new EmptyStatement(s.curLineNum());
		leaveParser("empty-statement");
		return es;
	}
}