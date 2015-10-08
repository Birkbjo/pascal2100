package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

public class Name extends Constant {

	Name(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<name> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	

}
