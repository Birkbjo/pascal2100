package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.Token;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class NumericLiteral extends Constant {
	Token t;
	public NumericLiteral(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<numeric-literal> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(Integer.toString(t.intVal));
		
	}
	
	public static NumericLiteral parse(Scanner s) {
		enterParser("numeric-literal");
		s.test(TokenKind.intValToken);
		NumericLiteral nl = new NumericLiteral(s.curLineNum());
		nl.t = s.curToken;
		s.readNextToken();
		
		leaveParser("numeric-literal");
		return nl;
	}

}
