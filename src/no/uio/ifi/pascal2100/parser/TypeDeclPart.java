package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class TypeDeclPart extends PascalSyntax {
	ArrayList<TypeDecl> typeDeclList = new ArrayList<TypeDecl>();
	
	public TypeDeclPart(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<type-decl-part> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("type");
		for(TypeDecl td : typeDeclList) {
			td.prettyPrint();
		}
	}
	
	public static TypeDeclPart parse(Scanner s) {
		enterParser("type-decl-part");
		s.skip(TokenKind.typeToken);
		TypeDeclPart tdp = new TypeDeclPart(s.curLineNum());

		while(s.curToken.kind == TokenKind.nameToken) {
			tdp.typeDeclList.add(TypeDecl.parse(s));
		}
		leaveParser("type-decl-part");
		return tdp;
	}

}
