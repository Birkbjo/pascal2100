package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

abstract public class Constant extends Factor{

	Constant(int lnum) {
		super(lnum);
	}

	public static Constant parse(Scanner s) {
		enterParser("constant");
		Constant con = null;
		switch(s.curToken.kind) {
		case intValToken:
			con = NumericLiteral.parse(s);
			break;
		case stringValToken:
			con = StringLiteral.parse(s);
			break;
		default:
			con = Name.parse(s);
			break;
		}
		leaveParser("constant");
		return con;
	}

}
