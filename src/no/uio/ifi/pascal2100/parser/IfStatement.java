package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

class IfStatement extends Statement {

	IfStatement(int n) {
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
	
	public static IfStatement parse(Scanner s) {
		enterParser("if-statement");
		
		leaveParser("if-statement");
		return null;
		
	}

}