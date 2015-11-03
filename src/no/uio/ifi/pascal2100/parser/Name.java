package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Name extends Constant {
	String name;
	ConstDecl ref;
	
	Name(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<name> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
	}
	
	void check(Block curScope, Library lib) {
		PascalDecl d = curScope.findDecl(name, this);
		ref = (ConstDecl)d;
		
	}
	
	public static Name parse(Scanner s) {
		enterParser("named constant");
		s.test(TokenKind.nameToken);
		Name n = new Name(s.curLineNum());
		n.name = s.curToken.id;
		s.readNextToken();
		leaveParser("named constant");
		return n;
	}
	

}
