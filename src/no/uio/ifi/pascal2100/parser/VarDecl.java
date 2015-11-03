package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class VarDecl extends PascalDecl {
	Type type;
	VarDecl(String id, int lNum) {
		super(id, lNum);
	}

	@Override
	public String identify() {
		return "<var-decl>" + (isInLibrary() ? " in the library" : " on line " + lineNum);
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
		Main.log.prettyPrint(": ");
		type.prettyPrint();
		Main.log.prettyPrintLn("; ");
	}

	public static VarDecl parse(Scanner s) {
		enterParser("var decl");
		s.test(TokenKind.nameToken);
		VarDecl vd = new VarDecl(s.curToken.id,s.curLineNum());
		s.readNextToken();
		s.skip(TokenKind.colonToken);
		vd.type = Type.parse(s);
		s.skip(TokenKind.semicolonToken);
		leaveParser("var decl");
		return vd;
	}

	public void check(Block curScope, Library lib) {
		type.check(curScope,lib);
		
	}

}
