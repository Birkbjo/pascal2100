package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class TypeDeclPart extends PascalSyntax {
	TypeDecl typedecl;
	public TypeDeclPart(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<type-decl-part> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub

	}
	
	public static TypeDeclPart parse(Scanner s) {
		enterParser("type-decl-part");
		s.skip(TokenKind.typeToken);
		TypeDeclPart tdp = new TypeDeclPart(s.curLineNum());

		while(s.curToken.kind == TokenKind.nameToken) {
			tdp.typedecl = TypeDecl.parse(s);
		}
		leaveParser("type-decl-part");
		return null;
	}

}
