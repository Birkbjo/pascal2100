package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class TypeDecl extends PascalDecl {
	Type type;
	
	TypeDecl(String id, int lNum) {
		super(id, lNum);
	}

	@Override
	public String identify() {
		return "<type-decl>" + (isInLibrary() ? " in the library" : " on line " + lineNum);
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
		Main.log.prettyPrint(" = ");
		type.prettyPrint();
		Main.log.prettyPrint(";");
	}
	
	void check(Block curScope,Library lib) {
		type.check(curScope,lib);
	}

	public static TypeDecl parse(Scanner s) {
		enterParser("type-decl");
		s.test(TokenKind.nameToken);
		TypeDecl td = new TypeDecl(s.curToken.id,s.curLineNum());
		s.readNextToken();
		s.skip(TokenKind.equalToken);
		td.type = Type.parse(s);
		s.skip(TokenKind.semicolonToken);
		leaveParser("type-decl");
		return td;
	}

}