package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

public class EnumLiteral extends PascalDecl {

	Token t;
	EnumLiteral(String id, int n) {
		super(id, n);
	}

	@Override
	public String identify() {
		return "<enum-literal> " + name + " on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(t.id);
	}

	public static EnumLiteral parse(Scanner s) {
		enterParser("enum-literal");
		
		s.test(TokenKind.nameToken);
		EnumLiteral el = new EnumLiteral(s.curToken.id, s.curLineNum());
		s.readNextToken();

		leaveParser("enum-literal");
		return el;
	}

}
