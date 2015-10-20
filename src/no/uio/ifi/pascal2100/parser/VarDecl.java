package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class VarDecl extends PascalDecl {
	Type type;
	VarDecl(String id, int lNum) {
		super(id, lNum);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
		Main.log.prettyPrint(": ");
		type.prettyPrint();
		Main.log.prettyPrint("; ");
	}

	public static VarDecl parse(Scanner s) {
		enterParser("var-decl");
		s.test(TokenKind.nameToken);
		VarDecl vd = new VarDecl(s.curToken.id,s.curLineNum());
		s.readNextToken();
		s.skip(TokenKind.colonToken);
		vd.type = Type.parse(s);
		s.skip(TokenKind.semicolonToken);
		leaveParser("var-decl");
		return vd;
	}

}